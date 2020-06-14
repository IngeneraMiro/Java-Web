package com.ingenera.springworkshop.web;

import com.ingenera.springworkshop.models.entities.Exercise;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final ExerciseService exerciseService;

    @Autowired
    public HomeController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/home")
    public ModelAndView home(ModelAndView modelAndView, HttpSession session){
        UserServiceModel user = (UserServiceModel)session.getAttribute("user");
        if(user!=null) {
            List<String> exercises = this.exerciseService.getActiveExercises(LocalDateTime.now()).
                    stream().map(Exercise::getName).collect(Collectors.toList());
            modelAndView.addObject("listex",exercises);
            modelAndView.setViewName("home");
        }else{
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }

}
