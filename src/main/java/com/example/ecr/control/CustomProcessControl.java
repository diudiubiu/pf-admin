package com.example.ecr.control;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import com.example.ecr.entity.RecentChallans;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.repository.RecentChallansRepository;
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
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class CustomProcessControl {

    static Logger log = LoggerFactory.getLogger(ExcelOperationControl.class);

    RecentEcrRepository recentEcrRepository;
    RecentChallansRepository recentChallansRepository;
    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Autowired
    public void setRecentChallansRepository(RecentChallansRepository recentChallansRepository) {
        this.recentChallansRepository = recentChallansRepository;
    }

    @Autowired
    public void setRecentEcrRepository(RecentEcrRepository recentEcrRepository) {
        this.recentEcrRepository = recentEcrRepository;
    }

    @PostMapping("/uploadPdfAndTxt")
    @ResponseBody
    public String uploadPdf(HttpServletRequest request) throws IOException {
        //log.info("-----id:{}", request.getParameter("id"));
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        //log.info("-----fileList0:{}", fileList);
        fileList.stream().filter(file -> !ObjectUtils.isEmpty(file)).forEach(file1 -> upload(file1, request.getParameter("id")));
        return "success";
    }

    @PostMapping("/uploadChallanPdfAndPaymentPdf")
    @ResponseBody
    public String uploadChallanPdfAndPaymentPdf(HttpServletRequest request) throws IOException {
        //log.info("-----id:{}", request.getParameter("id"));
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        //log.info("-----fileList0:{}", fileList);
        fileList.stream().filter(file -> !ObjectUtils.isEmpty(file)).forEach(file1 -> uploadcp(file1, request.getParameter("id")));
        return "success";
    }

    private void uploadcp(MultipartFile file, String id) {

        String filename = file.getOriginalFilename();
        BufferedOutputStream out = FileUtil.getOutputStream(uploadFolder + filename);
        try {
            long copySize = IoUtil.copy(file.getInputStream(), out, IoUtil.DEFAULT_BUFFER_SIZE);
            if (copySize > 0 && !StrUtil.isEmpty(id)) {
                RecentChallans recentChallans = recentChallansRepository.findById(Long.parseLong(id)).get();
                if (filename.indexOf("ECR") > -1) {
                    recentChallans.setChallanReceiptPath(filename);
                }
                if (filename.indexOf("Pay") > -1) {
                    recentChallans.setPaymentReceiptPath(filename);
                }
                recentChallans.setUploadStatus(true);

                recentChallansRepository.save(recentChallans);
            }

            //log.info("copySize:{}", copySize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(MultipartFile file, String id) {

        String filename = file.getOriginalFilename();
        BufferedOutputStream out = FileUtil.getOutputStream(uploadFolder + filename);
        try {
            long copySize = IoUtil.copy(file.getInputStream(), out, IoUtil.DEFAULT_BUFFER_SIZE);
            if (copySize > 0 && !StrUtil.isEmpty(id)) {
                RecentEcr recentEcr = recentEcrRepository.findById(Long.parseLong(id)).get();
                //RecentEcr RecentEcr = recentEcrRepository.getReferenceById(Long.parseLong(id));
                if (filename.indexOf(".pdf") > -1) {
                    recentEcr.setEcrStatementPath(filename);
                }
                if (filename.indexOf(".txt") > -1) {
                    recentEcr.setEcrFilePath(filename);
                }
                recentEcr.setUploadStatus(true);
                recentEcrRepository.save(recentEcr);
            }

            log.info("copySize:{}", copySize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(uploadFolder + fileName);
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
    @RequestMapping("/del")
    public String del(Long id) throws IOException {
        recentEcrRepository.deleteById(id);
        return "redirect:/txtpdf2pf4ecr";
    }


}
