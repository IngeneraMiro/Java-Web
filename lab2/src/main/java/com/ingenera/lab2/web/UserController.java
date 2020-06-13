package com.ingenera.lab2.web;

import com.ingenera.lab2.models.bindmodels.UserBindModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping("/login")
    public String loguser() {

        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/save")
    public String registerUser(@Valid @ModelAttribute("user") UserBindModel userBindModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

}
