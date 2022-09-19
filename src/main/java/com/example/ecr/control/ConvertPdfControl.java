package com.example.ecr.control;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.example.ecr.entity.RecentChallans;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.pojo.EmployeeData;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class ConvertPdfControl {

    static Logger log = LoggerFactory.getLogger(AdminControl.class);
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

    /**
     * 上传并复制excel原始文件到云端
     * @param model
     * @param file
     * @return
     * @throws IOException
     */
    public String upload6CopyExcel(Model model, MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis()+file.getOriginalFilename();
        BufferedOutputStream out = FileUtil.getOutputStream(uploadFolder + filename);
        long copySize = IoUtil.copy(file.getInputStream(), out, IoUtil.DEFAULT_BUFFER_SIZE);
        return null;
    }

    @RequestMapping("/creatRecentChallans")
    public String creatRecentChallans(Model model, Long id, int p) throws IOException {
        if (p > 0 && id > 0) {
            RecentChallans recentChallans = recentChallansRepository.findById(id).get();
            log.info("recentChallans{}", recentChallans);
        }
        return null;

    }

}
