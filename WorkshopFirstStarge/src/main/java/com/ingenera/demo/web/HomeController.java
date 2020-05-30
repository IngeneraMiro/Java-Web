package com.ingenera.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

//    @RequestMapping("/")
//    public String index(){
//     return "index";
//    }


    @GetMapping("/")
    public String test(){
        return "style.css";
    }
}
