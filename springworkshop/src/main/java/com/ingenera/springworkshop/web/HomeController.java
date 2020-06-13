package com.ingenera.springworkshop.web;

import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, HttpSession session){
        UserServiceModel user = (UserServiceModel)session.getAttribute("user");
        if(user!=null) {
            modelAndView.setViewName("home");
        }else{
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

}
