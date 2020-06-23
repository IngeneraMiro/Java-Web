package com.ingenera.oldexam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class InitController {

    @GetMapping("/")
    public String index(HttpSession session){

        return "index";
    }

}
