package com.example.ecr.control;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.ecr.entity.RecentChallans;
import com.example.ecr.entity.RecentEcr;
import com.example.ecr.pojo.ChallanData;
import com.example.ecr.repository.RecentChallansRepository;
import com.example.ecr.repository.RecentEcrRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class AdminControl {
    static Logger log = LoggerFactory.getLogger(AdminControl.class);
    static int showLine = 10;
    RecentEcrRepository recentEcrRepository;

    @RequestMapping("")
    public String defaultPage() {
        return epfo();
    }

    @RequestMapping("/epfo")
    public String epfo() {
        return "login-epfo";
    }

    @RequestMapping("/pdfpdf2pf4challans")
    public String pdfpdf2pf4challans(Model model, @RequestParam(value = "p", required = false, defaultValue = "1") int p) throws IOException {
        model.addAttribute("p", p);
        Page<RecentEcr> recentEcrPage = getRecentList(p);
        model.addAttribute("data", recentEcrPage);
        return "matching-pdfpdf2pf4challans";
    }

    @RequestMapping("/txtpdf2pf4ecr")
    public String txtpdf2pf4ecr(Model model, @RequestParam(value = "p", required = false, defaultValue = "1") int p) throws IOException {
        //log.info("p:{}", p);
        model.addAttribute("p", p);
        Page<RecentEcr> recentEcrPage = getRecentList(p);
        model.addAttribute("data", recentEcrPage);
        return "matching-txtpdf2pf4ecr";
    }

    @Autowired
    public void setRecentEcrRepository(RecentEcrRepository recentEcrRepository) {
        this.recentEcrRepository = recentEcrRepository;
    }

    public Page<RecentEcr> getRecentList(int p) throws IOException {
        Page<RecentEcr> recentEcrPage = recentEcrRepository.findAll(PageRequest.of(p - 1, showLine, Sort.by("id").descending()));
        return recentEcrPage;

    }


    @RequestMapping("/admin-login")
    public String adminLogin(String user, String pwd, HttpServletRequest request) {

        HttpSession mySession = request.getSession();
        mySession.setAttribute("userName", user);
        SimpleDateFormat sdf = new SimpleDateFormat("EE dd MMM yyyy", Locale.UK);
        mySession.setAttribute("epfoNow", sdf.format(new Date()));
        if (!pwd.equals("xq")) {
            return "/login-epfo";
        }
        return "redirect:/ecr6ReturnFiling";
    }

    @RequestMapping("/ecr6ReturnFiling")
    public String ecr6ReturnFiling(Model model, @RequestParam(value = "p", required = false, defaultValue = "1") int p) throws IOException {
        model.addAttribute("p", p);
        Page<RecentEcr> recentEcrPage = getRecentList(p);
        model.addAttribute("data", recentEcrPage);
        return "ecr6ReturnFiling";
    }

    @RequestMapping("/ecr6Payment")
    public String ecr6Payment(Model model, @RequestParam(value = "p", required = false, defaultValue = "1") int p) throws IOException {
        model.addAttribute("p", p);
        Page<RecentEcr> recentEcrPage = getRecentList(p);
        model.addAttribute("data", recentEcrPage);
        return "ecr6Payment";
    }

    @RequestMapping("/e2txt")
    public String e2txt() {
        return "upload-e2txt4upecr";
    }

    @RequestMapping("/e2ecr4pdf")
    public String e2ecr4pdf() throws IOException {
        return "upload-e2ecr4pdf";
    }

    /**
     * 打开 ecr 保存修改 页面
     *
     * @param model
     * @param id
     * @return
     * @throws IOException
     */
    @RequestMapping("/e2ecr4pdf6edit")
    public String e2ecr4pdf6edit(Model model,
                                 @RequestParam(value = "id", required = false, defaultValue = "1") Long id) throws IOException {
        model.addAttribute("id", id);
        RecentEcr recentEcr = recentEcrRepository.findById(id).get();
        //log.info("recentEcr{}", recentEcr);
        model.addAttribute("recentEcr", recentEcr);
        return "edit-e2ecr4pdf";
    }

    /**
     * 保存 修改 ecr
     *
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveOrUpdateRecentEcr")
    @ResponseBody
    public String edit2ecr4pdf(RecentEcr recentEcrPage) throws IOException {

        RecentEcr recentEcr = recentEcrRepository.findById(recentEcrPage.getId()).get();
        recentEcr.setUploadDate(recentEcrPage.getUploadDate());
        recentEcr.setTrrn(recentEcrPage.getTrrn());
        recentEcr.setWageMonth(recentEcrPage.getWageMonth());
        recentEcr.setSalaryDisbDate(recentEcrPage.getSalaryDisbDate());
        recentEcr.setEstablishmentId(recentEcrPage.getEstablishmentId());
        //log.info("recentEcr123 {}", recentEcr);
        recentEcrRepository.save(recentEcr);
        return "success";
    }

    @PostMapping("/saveOrUpdateRecentChallans")
    @ResponseBody
    public String edit2Challans4pdf(ChallanData challanData) throws IOException {
        RecentChallans recentChallansDB = recentChallansRepository.findById(challanData.getId()).get();
        recentChallansDB.setCRN(challanData.getCanNumber());
        recentChallansRepository.save(recentChallansDB);
        return "success";
    }

    RecentChallansRepository recentChallansRepository;

    @Autowired
    public void setRecentChallansRepository(RecentChallansRepository recentChallansRepository) {
        this.recentChallansRepository = recentChallansRepository;
    }

    @RequestMapping("/e2challans4pdf")
    public String e2challans4pdf(Model model,
                                 @RequestParam(value = "id", required = false, defaultValue = "1") Long id) throws IOException {
        model.addAttribute("id", id);
        RecentChallans recentChallans = recentChallansRepository.findById(id).get();
        //log.info("recentChallans{}", recentChallans);
        model.addAttribute("recentChallans", recentChallans);
        return "upload-e2challans4pdf";
    }

    @RequestMapping("/json")
    public String json(Model model) {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("data/json.data");
        String result = fileReader.readString();
        JSONObject json = JSONUtil.parseObj(result);
        model.addAttribute("htmlData", json);
        return "json";
    }

    @RequestMapping("/pdf")
    public String pdf() {
        return "pdf";
    }


}
