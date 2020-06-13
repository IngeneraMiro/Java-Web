package com.ingenera.springworkshop.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/rights")
    @ResponseBody
    public String right(){
        return "<h2>You have no rights to view this page !!!<h2>";
    }

    @GetMapping("/add_exercise")
    public ModelAndView addExercise(ModelAndView modelAndView, HttpSession session){
        System.out.println();
        if(session.getAttributeNames().hasMoreElements() && session.getAttribute("role").equals("ADMIN")){
            modelAndView.setViewName("exercise-add");
        }else{
            modelAndView.setViewName("redirect:/admin/rights");
        }
        return modelAndView;
    }


    @GetMapping("/add_role")
    public ModelAndView addRole(ModelAndView modelAndView,HttpSession session){
        if(session.getAttributeNames().hasMoreElements() && session.getAttribute("role").equals("ADMIN")){
            modelAndView.setViewName("role-add");
        }else{
            modelAndView.setViewName("redirect:/admin/rights");
        }
        return modelAndView;

    }

}
