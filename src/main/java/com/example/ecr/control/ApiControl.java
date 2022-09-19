package com.example.ecr.control;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.repository.RecentEcrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class ApiControl {

    static Logger log = LoggerFactory.getLogger(ApiControl.class);

    /**
     * cookie
     * home页 分别显示 name（
     * @param request
     * @return
     */
    @RequestMapping("/pf-login")
    @ResponseBody
    public String pfLogin(HttpServletRequest request) {
        String user = request.getParameter("user");
        //String pwd = request.getParameter("pwd");
        if (StrUtil.isEmpty(user)) {
            return "fail";
        }
        HttpSession mySession = request.getSession();
        SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM yyyy", Locale.UK);
        mySession.setAttribute("epfoNow", sdf.format(new Date()));
        if (user.indexOf("M") > -1) {
            user = "MRNOI2513599000";
        }
        if (user.indexOf("d") > -1) {
            user = "dsnhp2111338000";
        } else {
            user = "MRNOI2513599000";
        }
        mySession.setAttribute("userName", user);
        //mySession.setAttribute("UUID", IdUtil.simpleUUID().toUpperCase());

        //try {
        //    Thread.sleep(1000);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}

        //mySession.setAttribute("lin", "1867777921");
        return "success";
    }

    RecentEcrRepository recentEcrRepository;
    @Autowired
    public void setRecentEcrRepository(RecentEcrRepository recentEcrRepository) {
        this.recentEcrRepository = recentEcrRepository;
    }
    @Value("${file.uploadFolder}")
    private String pfUploadFolder;

    /**
     * ECR showLine==20 （分页，正序）；
     * Payment&Challan showLine ==10；
     * @param model
     * @param p
     * @param showLine
     * @return
     */
    @GetMapping("/getRecentList")
    @ResponseBody
    public Page<RecentEcr> getRecentList(Model model,
                                         @RequestParam(value = "userName", required = true, defaultValue = "MRNOI2513599000") String userName,
                                         @RequestParam(value = "p", required = true, defaultValue = "1") int p,
                                         @RequestParam(value = "showLine", required = true, defaultValue = "20") int showLine){

        log.info("/getRecentList--userName{}",userName);
        Page<RecentEcr> recentEcrPage = recentEcrRepository.
                findAllByEstablishmentId(PageRequest.of(p - 1, showLine, Sort.by("id").descending()),userName);
        return recentEcrPage;

    }

    //todo
    @GetMapping("/pf-download")
    public void download(String fileName, HttpServletResponse response){
        File file = new File(pfUploadFolder + fileName);
        byte[] fileData = FileUtil.readBytes(file);
        // 将文件内容 byte[]，通过 response 返回给客户端进行下载
        if (fileData.length > 0) {
            try {
                response.setContentType("application/octet-stream");
                //response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-disposition", "attachment; filename=" + fileName);
                response.setHeader("Content-Length", String.valueOf(fileData.length));
                response.getOutputStream().write(fileData);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
