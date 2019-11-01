package com.example.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@Controller
public class SpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/field")
    public String field(Model model) {
        // 设置用户对象
        model.addAttribute("user", new User("crazyit", "lady"));
        // 设置性别常量，集合中存放字符串
        List<String> sexCons = new ArrayList<String>();
        sexCons.add("man");
        sexCons.add("lady");
        model.addAttribute("sexCons", sexCons);
        return "field";
    }

    @PostMapping("/field_submit")
    public String submit(@ModelAttribute User user) {
        System.out.println("表单对象: " + user.getUserName());
        return "index";
    }

    @GetMapping("/form_field")
    public String form_field(Model model) {
        // 设置常量到Model中
        setConstant(model);
        // 创建User
        ColorUser user = new ColorUser();
        // 设置用户喜欢的颜色
        user.setMyColors(new String[]{"yellow", "green"});
        // 设置性别
        user.setSex("lady");
        // 将用户对象设置到Model中
        model.addAttribute("user", user);
        return "form";
    }

    @PostMapping("/form_submit")
    public String form_submit(@ModelAttribute ColorUser user, Model model) {
        // 设置常量到Model中
        setConstant(model);
        model.addAttribute("user", user);
        System.out.println("性别：" + user.getSex());
        System.out.println("喜欢颜色：" + Arrays.toString(user.getMyColors()));
        return "form";
    }

    private void setConstant(Model model) {
        // 定义可选 的颜色
        String[] colors = new String[]{"red", "yellow", "green"};
        // 定义可选的性别
        String[] sexes = new String[]{"man", "lady"};
        // 将常量数据设置到Model中
        model.addAttribute("colors", colors);
        model.addAttribute("sexes", sexes);
    }

    @GetMapping("/ids_field")
    public String ids_field(Model model) {
        FormObject formObject = new FormObject();
        formObject.setName("crazyit");
        model.addAttribute("formObject", formObject);
        return "ids";
    }

}
