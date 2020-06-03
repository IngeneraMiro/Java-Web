package com.ingenera.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home2Controller {

    @GetMapping("/home")
    public String homeVisit(){
        return "home";
    }

}
