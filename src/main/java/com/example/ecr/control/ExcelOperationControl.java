package com.example.ecr.control;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.ecr.entity.RecentChallans;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.pojo.ChallanData;
import com.example.ecr.pojo.EmployeeData;
import com.example.ecr.pojo.HtmlData;
import com.example.ecr.pojo.MemberDetails;
import com.example.ecr.repository.RecentChallansRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ecr.util.ExcelOperationHelp.assembleTxtName;

/**
 * @author Hs
 */
@Controller
public class ExcelOperationControl {

    static Logger log = LoggerFactory.getLogger(ExcelOperationControl.class);

    RecentEcrRepository recentEcrRepository;

    RecentChallansRepository recentChallansRepository;
    double startTableHigh = 1316 - 138 - 55;
    double middleTableHigh = 1371 - 138 - 55;
    double endTableHigh = 1371 - 138 - 55 - 631;

    @Autowired
    public void setRecentChallansRepository(RecentChallansRepository recentChallansRepository) {
        this.recentChallansRepository = recentChallansRepository;
    }

    @Autowired
    public void setRecentEcrRepository(RecentEcrRepository recentEcrRepository) {
        this.recentEcrRepository = recentEcrRepository;
    }



    @PostMapping("/excel2txt")
    @ResponseBody
    public String excel2txt(MultipartFile file, String txtName, HttpServletResponse response) throws IOException {
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
                response.setHeader("Content-disposition", "attachment; filename=" + assembleTxtName(txtName));
                response.setHeader("Content-Length", String.valueOf(fileData.length));
                response.getOutputStream().write(fileData);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

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

        //todo
        String pdfName = "";

        String establishmentId = employeeData.getEstablishmentId();
        if (establishmentId.equals("MRNOI2513599000")) {
            employeeData.setLin("1867777921");
            pdfName = ExcelOperationHelp.assemblePdfName("MRNOI2513599000", employeeData.getEcrId());
            employeeData.setPdfName(pdfName);
            employeeData.setCorporateName("PRIMEWINGS SERVICE PRIVATE LIMITED");
        }
        if (establishmentId.equals("DSNHP2111338000")) {
            employeeData.setLin("1315060570");
            pdfName = ExcelOperationHelp.assemblePdfName("DSNHP2111338000", employeeData.getEcrId());
            employeeData.setPdfName(pdfName);
            employeeData.setCorporateName("SENYAR INDIA PRIVATE LIMITED");
        }
        //todo
        htmlData.setEmployeeData(employeeData);
        htmlData.setPageDate(pageDataMap);
        JSONObject json = JSONUtil.parseObj(htmlData);
        //log.info("json:,{}", json);


        //DSNHP2111338000_69549346_1645421713009_2022022139913009442
        //String txtName = ExcelOperationHelp.assembleTxtName("");

        model.addAttribute("htmlData", json);

        //log.info("htmlData:,{}", json);
        //FileWriter writer = new FileWriter("json.data");
        //writer.write(String.valueOf(json));

        RecentEcr recentEcr = new RecentEcr();
        RecentChallans recentChallans = new RecentChallans();

        //trrn
        if (StrUtil.isEmpty(employeeData.getTrrnNumber())) {
            recentEcr.setTrrn(ExcelOperationHelp.trrn());
        } else {
            recentEcr.setTrrn(employeeData.getTrrnNumber());
        }
        recentEcr.setWageMonth(employeeData.getWageMonth());
        recentEcr.setEcrType("ECR");
        recentEcr.setSalaryDisbDate(employeeData.getSalaryDisbursementDate());
        recentEcr.setContrRate("12");
        recentEcr.setUploadDate(employeeData.getUploadedDateTime());
        recentEcr.setStatus("Payment confirmed");
        //recentEcr.setEcrFilePath(txtName);
        recentEcr.setEcrStatementPath(pdfName);
        recentEcr.setEstablishmentId(employeeData.getEstablishmentId());

        recentEcr.setRecentChallans(recentChallans);
        //recentChallans.setRecentEcr(recentEcr);
        recentEcrRepository.save(recentEcr);
        model.addAttribute("pdfName", pdfName);
        //model.addAttribute("txtName", txtName);
        ChallanData challanData = new ChallanData();
        challanData.setId(recentChallans.getId());
        excel2json4Challans(file, challanData);
        return "json";
    }


    /**
     * 这里 其实是修改  Challans
     * <p>
     *  todo 没有上传文件了
     *
     * @param file
     * @param challanData
     * @return
     * @throws IOException
     */
    @PostMapping("/excel2json4Challans")
    @ResponseBody
    public String excel2json4Challans(MultipartFile file, ChallanData challanData) throws IOException {

        RecentChallans recentChallansDB = new RecentChallans();
        long challanDataId = challanData.getId();
        if (NumberUtil.isNumber(String.valueOf(challanDataId))) {
            recentChallansDB = recentChallansRepository.findById(challanDataId).get();
            //log.info("{}", recentChallansDB);
        }
        //如果不上传文件，就直接更新can。
        if (file == null || file.isEmpty()) {
            recentChallansDB.setCRN(challanData.getCanNumber());
            recentChallansRepository.save(recentChallansDB);

        } else {
            final double d0e12 = 0.12;
            final double d0e0833 = 0.0833;
            final double d0e005 = 0.005;
            double dd, dg, dh, di, df;
            double dac2 = 0;
            double ac1 = 0, ac2 = 0, ac10 = 0, ac21 = 0;
            double ac22 = 0, totalAmt = 0;
            ExcelReader reader = ExcelUtil.getReader(file.getInputStream());
            List<List<Object>> readAll = reader.read();
            for (List<Object> objects : readAll) {
                //excel中获取- D -列数据。
                String dStr = objects.get(4 - 1).toString();
                String fStr = objects.get(6 - 1).toString();
                //判断是否为数字。
                if (NumberUtil.isNumber(dStr) && NumberUtil.isNumber(fStr)) {
                    dd = Double.parseDouble(dStr);
                    //dg = NumberUtil.mul(dd, d0e12);//乘
                    //dh = NumberUtil.mul(dd, d0e0833);//乘
                    //di = NumberUtil.sub(dg, dh);//减
                    dg = dd * d0e12;//乘
                    dh = dd * d0e0833;//乘
                    di = dg - dh;//减
                    df = Double.parseDouble(dStr);
                    //todo challan Pdf中 ac1 = g+i
                    //汇总 ac1 = 四舍五入（g）+（i）
                    ac1 += NumberUtil.round(dg, 0).doubleValue() + NumberUtil.round(di, 0).doubleValue();
                    //汇总 dac2 = 四舍五入2位小数（f*0.005）
                    dac2 += NumberUtil.round(NumberUtil.mul(df, d0e005), 2).doubleValue();
                    //汇总 ac10 = 四舍五入（h）
                    ac10 += NumberUtil.round(dh, 0).doubleValue();
                    //汇总 ac10 = 四舍五入（f）
                    ac21 += NumberUtil.round(NumberUtil.mul(df, d0e005), 0).doubleValue();
                    //log.info("\nd:{}\ng:{}\nh:{}\ni:{}", dd, dg, dh, di);
                }
            }
            ac2 = NumberUtil.round(dac2, 0).doubleValue();
            //log.info("\nac1:{}\nac2:{}\nac10:{}\nac21:{}", ac1, ac2, ac10, ac21);
            //log.info("\nac1:{}\nac2:{}\nac10:{}\nac21:{}",
            //        ExcelOperationHelp.lakhFormattedComma(ac1),
            //        ExcelOperationHelp.lakhFormattedComma(ac2),
            //        ExcelOperationHelp.lakhFormattedComma(ac10),
            //        ExcelOperationHelp.lakhFormattedComma(ac21));
            totalAmt = ac1 + ac2 + ac10 + ac21 + ac22;

            recentChallansDB.setAC1(ExcelOperationHelp.lakhFormattedComma(ac1));
            recentChallansDB.setAC2(ExcelOperationHelp.lakhFormattedComma(ac2));
            recentChallansDB.setAC10(ExcelOperationHelp.lakhFormattedComma(ac10));
            recentChallansDB.setAC21(ExcelOperationHelp.lakhFormattedComma(ac21));
            recentChallansDB.setAC22("0");
            if (StrUtil.isEmpty(challanData.getCanNumber())) {
                recentChallansDB.setCRN(ExcelOperationHelp.CanNumber());
            } else {
                recentChallansDB.setCRN(challanData.getCanNumber());
            }
            recentChallansDB.setTotalAmt(ExcelOperationHelp.lakhFormattedComma(totalAmt));

            recentChallansRepository.save(recentChallansDB);
        }
        //recentChallansDB.getId();
        //model.addAttribute("pdfName", pdfName);
        //log.info("excel2json4Challans:{},{},{},{}", ac1, ac2, ac10, ac21);
        return "success";
    }
}
