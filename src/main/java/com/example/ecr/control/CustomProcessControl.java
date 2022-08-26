package com.example.ecr.control;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.repository.RecentEcrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class CustomProcessControl {

    static Logger log = LoggerFactory.getLogger(ExcelOperationControl.class);

    RecentEcrRepository recentEcrRepository;

    @Autowired
    public void setRecentEcrRepository(RecentEcrRepository recentEcrRepository) {
        this.recentEcrRepository = recentEcrRepository;
    }


    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @PostMapping("/uploadPdfAndTxt")
    @ResponseBody
    public String uploadPdf(HttpServletRequest request) throws IOException {
        //FileReader fileReader = new FileReader("/Users/chenchencui/Downloads/upload/1.txt");
        //log.info("--------{}",fileReader.getInputStream());
        //String realPath = request.getSession().getServletContext().getRealPath(uploadFolder);
        //String realPath2 = request.getSession().getServletContext().getRealPath("/upload/");
        //log.info("-----{},---{}--,{}",uploadFolder,realPath,realPath2);
        log.info("-----id:{}", request.getParameter("id"));
        List<MultipartFile> fileList = ((MultipartHttpServletRequest) request).getFiles("file");
        log.info("-----fileList0:{}", fileList.get(0));

        fileList.stream().filter(file -> !ObjectUtils.isEmpty(file)).forEach(file1 -> upload(file1, request.getParameter("id")));
        return "success";
    }

    public static void main(String[] args) {

        File pathFolder = FileUtil.mkdir("/Users/chenchencui/Downloads/upload" + File.separatorChar + DateUtil.today());
        //BufferedOutputStream out = FileUtil.getOutputStream(pathFolder + "2022-08-25 16/12/59up1659404585965.txt");
        //log.info("1111{}", DateUtil.today());

        //String formatDate = DateUtil.format(date2, DatePattern.PURE_DATETIME_MS_FORMAT);
        //String formatDate = DateUtil.format(date2, "yyyyMMddHHmmssZ");
        Date date2 = DateUtil.date(Calendar.getInstance());
        String formatDate = DateUtil.format(date2, "yyyyMMddHHmmssSSSSS");

        log.info("{},{}", "2022022139913009442", "2022022139913009442".length());
        log.info("{},{}", formatDate, formatDate.length());

    }

    private void upload(MultipartFile file, String id) {


        String filename = file.getOriginalFilename();
        //String day =  DateUtil.today();
        //File pathFolder = FileUtil.mkdir(uploadFolder);
        //String statementPath = uploadFolder + filename;
        BufferedOutputStream out = FileUtil.getOutputStream(uploadFolder + filename);
        try {
            long copySize = IoUtil.copy(file.getInputStream(), out, IoUtil.DEFAULT_BUFFER_SIZE);
            if (copySize > 0 && !StrUtil.isEmpty(id)) {
                RecentEcr recentEcr = recentEcrRepository.getReferenceById(Long.parseLong(id));
                recentEcr.setEcrStatementPath(filename);
                recentEcr.setHasUploadFile(true);
                recentEcrRepository.saveAndFlush(recentEcr);
            }

            log.info("{}", copySize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getRecentList")
    @ResponseBody
    public Page<RecentEcr> getRecentList() throws IOException {

        Page<RecentEcr> recentEcrPage = recentEcrRepository.findAll((Specification<RecentEcr>) null, PageRequest.of(0, 10));

        //JSONObject json = JSONUtil.parseObj(recentEcrPage);
        //log.info("----{}", json);

        return recentEcrPage;

    }

    @PostMapping("/downloadNet")
    public void downloadNet(String filePath, HttpServletResponse response) throws IOException {
        String fileUrl = "http://127.0.0.1:8002/upload/" + filePath;
        //将文件下载后保存在E盘，返回结果为下载文件大小
        HttpUtil.downloadFile(fileUrl, FileUtil.file(""), new StreamProgress() {
            @Override
            public void start() {
                //Console.log("开始下载。。。。");
            }

            @Override
            public void progress(long l, long l1) {
                //Console.log("已下载：{}", l);
            }

            @Override
            public void finish() {
                //Console.log("下载完成！");
            }
        });
    }

    @GetMapping("/download")
    public void download(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(uploadFolder + fileName);
        byte[] fileData = FileUtil.readBytes(file);
        // 将文件内容 byte[]，通过 response 返回给客户端进行下载
        if (fileData.length > 0) {
            try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-Length", String.valueOf(fileData.length));
                response.getOutputStream().write(fileData);
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
