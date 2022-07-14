package com.example.ecr.control;


import cn.hutool.core.io.file.FileWriter;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.ecr.pojo.EmployeeData;
import com.example.ecr.pojo.HtmlData;
import com.example.ecr.pojo.MemberDetails;
import com.example.ecr.util.ExcelOperationHelp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hs
 */
@Controller
public class ExcelOperationControl {

    static Logger log = LoggerFactory.getLogger(ExcelOperationControl.class);


    double startTableHigh = 1316 - 138 - 55;
    double middleTableHigh = 1371 - 138 - 55;
    double endTableHigh = 1371 - 138 - 631 + 100;
    double rowHigh = 0;
    int linePx = 1;
    int page = 1;
    int siNo = 0;
    double gSum = 0, hSum = 0, iSum = 0;
    String gStr, hStr, iStr;
    HtmlData htmlData = new HtmlData();
    List<MemberDetails> memberDetailsList = new ArrayList<>();
    Map<Integer, List<MemberDetails>> pageDataMap = new HashMap<Integer, List<MemberDetails>>();


    @PostMapping("/excel2html")
    public String excel2html(Model model, MultipartFile file, EmployeeData employeeData) throws IOException {
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
            memberDetails.setECR(ExcelOperationHelp.ecrStrOpt(strEcr));
            memberDetails.setUANRepository(ExcelOperationHelp.uanStrOpt(strEcr.toUpperCase()));
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

            memberDetails.setNCPDays(String.valueOf(objects.get(9)));
            memberDetails.setRefunds(String.valueOf(objects.get(10)));

            if (rowHigh >= startTableHigh && page == 1) {
                clearAndSave();
            }
            if (rowHigh >= middleTableHigh && page > 1) {
                clearAndSave();
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
        //log.info("htmlData:,{}", htmlData);
        JSONObject json = JSONUtil.parseObj(htmlData);
        model.addAttribute("htmlData", json);
        log.info("htmlData:,{}", json);
        //FileWriter writer = new FileWriter("json.data");
        //writer.write(String.valueOf(json));
        return "json";
    }

    void clearAndSave() {
        pageDataMap.put(page, memberDetailsList);
        memberDetailsList = new ArrayList<>();
        rowHigh = 0;
        page = page + 1;
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

        byte[] fileData = strAll.toString().getBytes();

        // 将文件内容 byte[]，通过 response 返回给客户端进行下载
        if (fileData.length > 0) {
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
