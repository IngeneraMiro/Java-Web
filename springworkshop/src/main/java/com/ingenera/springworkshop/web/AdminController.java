package com.ingenera.springworkshop.web;

import com.ingenera.springworkshop.models.bindmodels.ExerciseBindModel;
import com.ingenera.springworkshop.models.bindmodels.UserBindModel;
import com.ingenera.springworkshop.services.ExerciseService;
import com.ingenera.springworkshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ExerciseService exerciseService;
    private final UserService userService;

    @Autowired
    public AdminController(ExerciseService exerciseService, UserService userService) {
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

    @GetMapping("/rights")
    @ResponseBody
    public String right(){
        return "<h2>You have no rights to view this page !!!<h2>";
    }


    @GetMapping("/add_exercise")
    public ModelAndView addExercise(@Valid @ModelAttribute("exerciseBindModel")ExerciseBindModel exerciseBindModel,
                                    BindingResult result,ModelAndView modelAndView, HttpSession session){
        System.out.println();
        if(session.getAttributeNames().hasMoreElements() && session.getAttribute("role").equals("ADMIN")){
            modelAndView.addObject("exerciseBindModel",exerciseBindModel);
            modelAndView.setViewName("exercise-add");
        }else{
            modelAndView.setViewName("redirect:/admin/rights");
        }
        return modelAndView;
    }


    @PostMapping("/add_exercise")
    public ModelAndView exerciseValidate(@Valid @ModelAttribute("exerciseBindModel")ExerciseBindModel exerciseBindModel,
                                         BindingResult result, HttpSession session, RedirectAttributes redirectAttributes,
                                         ModelAndView modelAndView){
        if(session!=null) {
            if (result.hasErrors()) {
                redirectAttributes.addFlashAttribute("exerciseBindModel", exerciseBindModel);
                modelAndView.setViewName("redirect:/admin/add_exercise");
            } else {
                  this.exerciseService.createNewExercise(exerciseBindModel);
                  modelAndView.setViewName("redirect:/home");
            }
        }else{
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }


    @GetMapping("/add_role")
    public ModelAndView addRole(ModelAndView modelAndView,HttpSession session){

        if(session.getAttributeNames().hasMoreElements() && session.getAttribute("role").equals("ADMIN")){
            List<String> users = this.userService.getAllUsers().stream().map(UserBindModel::getUsername).collect(Collectors.toList());
            modelAndView.addObject("users",users);
            modelAndView.setViewName("role-add");
        }else{
            modelAndView.setViewName("redirect:/admin/rights");
        }
        return modelAndView;
    }

    @PostMapping("/role_add")
    public ModelAndView changeRole(ModelAndView modelAndView,@RequestParam String username,@RequestParam String role,
                                   HttpSession session){
        if(session.getAttribute("role").equals("ADMIN")) {
            this.userService.changeRole(username, role);
            modelAndView.setViewName("redirect:/home");
        }else {
            modelAndView.setViewName("redirect:/admin/rights");
        }
        return modelAndView;
    }

}
