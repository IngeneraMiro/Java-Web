package com.ingenera.springworkshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ErrorController {

    @GetMapping("/error")
    public String error(RedirectAttributes redirectAttributes){
        String error = (String)redirectAttributes.getFlashAttributes().get("error");
        return error;
    }

}
