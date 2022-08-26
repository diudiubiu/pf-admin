package com.example.ecr.control;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.pojo.EmployeeData;
import com.example.ecr.pojo.HtmlData;
import com.example.ecr.pojo.MemberDetails;
import com.example.ecr.repository.RecentEcrRepository;
import com.example.ecr.util.ExcelOperationHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Hs
 */
@Controller
public class ExcelOperationControl {

    static Logger log = LoggerFactory.getLogger(ExcelOperationControl.class);

    RecentEcrRepository recentEcrRepository;

    @Autowired
    public void setRecentEcrRepository(RecentEcrRepository recentEcrRepository) {
        this.recentEcrRepository = recentEcrRepository;
    }

    double startTableHigh = 1316 - 138 - 55;
    double middleTableHigh = 1371 - 138 - 55;
    double endTableHigh = 1371 - 138 - 55 - 631;

    @PostMapping("/excel2json")
    public String excel2html(Model model, MultipartFile file, EmployeeData employeeData) throws IOException {

        int linePx = 0, page = 1, siNo = 0;
        double rowHigh = 0, gSum = 0, hSum = 0, iSum = 0;
        String gStr, hStr, iStr;

        HtmlData htmlData = new HtmlData();
        List<MemberDetails> memberDetailsList = new ArrayList<>();
        Map<Integer, List<MemberDetails>> pageDataMap = new HashMap<Integer, List<MemberDetails>>();


        //log.info("-----EmployeeData,{}", employeeData);
        if (file.isEmpty()) {
            return null;
        }
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<List<Object>> readAll = reader.read();
        for (List<Object> objects : readAll) {
            MemberDetails memberDetails = new MemberDetails();
            memberDetails.setSiNo(++siNo);
            memberDetails.setUAN(String.valueOf(objects.get(0)));
            String strEcr = String.valueOf(objects.get(1));
            double rh = ExcelOperationHelp.rowHigh(strEcr);
            memberDetails.setRowHigh(rh);
            rowHigh += rh + linePx;

            //memberDetails.setECR(ExcelOperationHelp.ecrStrOpt(strEcr));
            memberDetails.setECR(strEcr);

            memberDetails.setUANRepository(ExcelOperationHelp.uanStrOpt(strEcr.toUpperCase()));
            //memberDetails.setUANRepository(strEcr.toUpperCase());

            memberDetails.setGross(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(String.valueOf(objects.get(2)))));
            memberDetails.setEPF(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(String.valueOf(objects.get(3)))));
            memberDetails.setEPS(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(String.valueOf(objects.get(4)))));
            memberDetails.setEDLI(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(String.valueOf(objects.get(5)))));
            //g
            gStr = String.valueOf(objects.get(6));
            gSum += Double.parseDouble(gStr);
            memberDetails.setEE(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(gStr)));
            //h
            hStr = String.valueOf(objects.get(7));
            hSum += Double.parseDouble(hStr);
            memberDetails.setCREPS(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(hStr)));
            //i
            iStr = String.valueOf(objects.get(8));
            iSum += Double.parseDouble(iStr);
            memberDetails.setER(ExcelOperationHelp.lakhFormattedComma(Double.parseDouble(iStr)));

            memberDetails.setNCPDays(ExcelOperationHelp.isNumberForRound(String.valueOf(objects.get(9))));
            memberDetails.setRefunds(String.valueOf(objects.get(10)));

            if (rowHigh >= startTableHigh && page == 1) {
                pageDataMap.put(page, memberDetailsList);
                memberDetailsList = new ArrayList<>();
                rowHigh = 0;
                page = page + 1;
            }
            if (rowHigh >= middleTableHigh && page > 1) {
                pageDataMap.put(page, memberDetailsList);
                memberDetailsList = new ArrayList<>();
                rowHigh = 0;
                page = page + 1;
            }

            memberDetailsList.add(memberDetails);
        }
        if (rowHigh < endTableHigh) {
            htmlData.setIndependentPage(true);
        }
        pageDataMap.put(page, memberDetailsList);
        htmlData.setTotalEPFContributionRemitted(ExcelOperationHelp.lakhFormattedComma(gSum));
        htmlData.setTotalEPSContributionRemitted(ExcelOperationHelp.lakhFormattedComma(hSum));
        htmlData.setTotalEPFEPSContributionRemitted(ExcelOperationHelp.lakhFormattedComma(iSum));
        htmlData.setPageCount(page);
        htmlData.setTotalNumber(siNo);
        htmlData.setEmployeeData(employeeData);
        htmlData.setPageDate(pageDataMap);
        JSONObject json = JSONUtil.parseObj(htmlData);
        //log.info("json:,{}", json);
        model.addAttribute("htmlData", json);

        //log.info("htmlData:,{}", json);
        //FileWriter writer = new FileWriter("json.data");
        //writer.write(String.valueOf(json));

        //DSNHP2111338000_69549346_1645421713009_2022022139913009442
        String pdfName = ExcelOperationHelp.assemblePdfName(employeeData.getEcrId());
        String txtName = "Shashi april_" + "123456789";
        RecentEcr recentEcr = new RecentEcr();
        recentEcr.setTrrn(employeeData.getTrrnNumber());
        recentEcr.setWageMonth(employeeData.getWageMonth());
        recentEcr.setEcrType("ECR");
        recentEcr.setSalaryDisbDate(employeeData.getSalaryDisbursementDate());
        recentEcr.setContrRate("12");
        recentEcr.setUploadDate(employeeData.getUploadedDateTime());
        recentEcr.setStatus("Payment confirmed");
        recentEcr.setEcrFilePath(txtName);
        recentEcr.setEcrStatementPath(pdfName);
        recentEcrRepository.save(recentEcr);

        model.addAttribute("pdfName", pdfName);

        return "json";
    }

    @PostMapping("/excel2txt")
    @ResponseBody
    public String excel2txt(MultipartFile file, HttpServletResponse response) throws IOException {
        if (file.isEmpty()) {
            return "failure";
        }
        ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
        List<List<Object>> readAll = reader.read();
        StringBuilder strAll = new StringBuilder(new StringBuffer());
        for (List<Object> objects : readAll) {
            StringBuilder strH = new StringBuilder();
            for (Object object : objects) {
                String str = ExcelOperationHelp.isNumberForRound(ExcelOperationHelp.strTrimExt(String.valueOf(object)));
                if (!str.isEmpty()) {
                    //去除空格，nbsp，每个字符串加"#～#"。
                    strH.append(str).append("#~#");
                }
            }
            strAll.append(strH).append("\n");
        }
        if (strAll.length() == 0) {
            return "failure";
        }
        //去掉最后的 \n.
        String txtStr = StrUtil.sub(strAll.toString(), 0, -1);
        byte[] fileData = txtStr.getBytes();

        // 将文件内容 byte[]，通过 response 返回给客户端进行下载
        if (fileData != null && fileData.length > 0) {
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename=up" + System.currentTimeMillis() + ".txt");
                response.setHeader("Content-Length", String.valueOf(fileData.length));
                response.getOutputStream().write(fileData);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }


}
