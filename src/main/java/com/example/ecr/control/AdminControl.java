package com.example.ecr.control;

import cn.hutool.core.io.file.FileReader;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminControl {

    @RequestMapping("")
    public String defaultPage() {
        return index();
    }
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("/e2txt")
    public String e2txt() {
        return "e2txt";
    }
    @RequestMapping("/e2pdf")
    public String e2pdf() {
        return "e2pdf";
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
