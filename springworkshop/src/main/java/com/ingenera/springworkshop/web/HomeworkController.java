package com.ingenera.springworkshop.web;

import com.ingenera.springworkshop.models.bindmodels.HomeworkBindModel;
import com.ingenera.springworkshop.models.entities.Exercise;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.services.ExerciseService;
import com.ingenera.springworkshop.services.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("homework")
public class HomeworkController {
    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
    }

    @GetMapping("/add")
    public ModelAndView addHomework(@Valid @ModelAttribute("homeworkBindModel")HomeworkBindModel homeworkBindModel,
                                    BindingResult result,ModelAndView modelAndView){
        List<String> exlist= this.exerciseService.getAllExercise();
        modelAndView.addObject("listex",exlist);
        modelAndView.addObject("homeworkBindModel",homeworkBindModel);
        modelAndView.setViewName("homework-add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView postHomework(@Valid @ModelAttribute("homeworkBindModel")HomeworkBindModel homeworkBindModel,
                                     BindingResult result, ModelAndView modelAndView, HttpSession session,
                                     RedirectAttributes redirectAttributes){
        if(session==null){
            redirectAttributes.addFlashAttribute("error","You have not rights to be here !!!");
            modelAndView.setViewName("redirect:/error");
            return modelAndView;
        }
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("homeworkBindModel",homeworkBindModel);
            modelAndView.setViewName("redirect:/homework/add");
        }else{
                if(exerciseService.getExerciseByName(homeworkBindModel.getExercise()).getDueDate().isBefore(LocalDateTime.now())){
                redirectAttributes.addFlashAttribute("youLate",true);
                modelAndView.setViewName("redirect:/homework/add");
            }else{
                this.homeworkService.addHomework(homeworkBindModel, (UserServiceModel) session.getAttribute("user"));
                modelAndView.setViewName("redirect:/home");
            }
        }
        return modelAndView;
    }


    @GetMapping("/evaluation")
    public ModelAndView evaluateHomework(ModelAndView modelAndView){

        modelAndView.setViewName("homework-check");
        return modelAndView;
    }

}
