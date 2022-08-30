package com.example.ecr.control;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminControl {

    @RequestMapping("")
    public String defaultPage() {
        return ecr6ReturnFiling();
    }

    @RequestMapping("/epfo")
    public String epfo() {
        return "login-epfo";
    }

    @RequestMapping("/txtpdf2pf4ecr")
    public String txtpdf2pf4ecr() {
        return "matching-txtpdf2pf4ecr";
    }
    @RequestMapping("/pdfpdf2pf4challans")
    public String pdfpdf2pf4challans() {
        return "matching-pdfpdf2pf4challans";
    }

    @RequestMapping("/login")
    public String login(String user, String pwd, HttpServletRequest request) {
        HttpSession mySession = request.getSession();
        mySession.setAttribute("userName", user);
        mySession.setAttribute("lin", "1867777921");

        return "ecr6ReturnFiling";
    }

    @RequestMapping("/ecr6ReturnFiling")
    public String ecr6ReturnFiling() {
        return "ecr6ReturnFiling";
    }
    @RequestMapping("/ecr6Payment")
    public String ecr6Payment() {
        return "ecr6Payment";
    }
    @RequestMapping("/e2txt")
    public String e2txt() {
        return "upload-e2txt4upecr";
    }

    @RequestMapping("/e2ecr4pdf")
    public String e2ecr4pdf() {
        return "upload-e2ecr4pdf";
    }

    @RequestMapping("/e2challans4pdf")
    public String e2challans4pdf() {
        return "upload-e2challans4pdf";
    }

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("json")
    public String json(Model model) {
        //默认UTF-8编码，可以在构造中传入第二个参数做为编码
        FileReader fileReader = new FileReader("static/json.data");
        String result = fileReader.readString();
        JSONObject json = JSONUtil.parseObj(result);
        model.addAttribute("htmlData", json);
        return "json";
    }

    @RequestMapping("pdf")
    public String pdf() {
        return "pdf";
    }
}
