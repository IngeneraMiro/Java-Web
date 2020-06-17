package com.ingenera.springworkshop.web;

import com.ingenera.springworkshop.models.bindmodels.HomeworkBindModel;
import com.ingenera.springworkshop.models.bindmodels.ScoreBindModel;
import com.ingenera.springworkshop.models.viewmodels.HomeworkViewModel;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.models.viewmodels.UserViewModel;
import com.ingenera.springworkshop.services.CommentService;
import com.ingenera.springworkshop.services.ExerciseService;
import com.ingenera.springworkshop.services.HomeworkService;
import com.ingenera.springworkshop.services.UserService;
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

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    private final ExerciseService exerciseService;
    private final HomeworkService homeworkService;
    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService, CommentService commentService, UserService userService) {
        this.exerciseService = exerciseService;
        this.homeworkService = homeworkService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public ModelAndView addHomework(@Valid @ModelAttribute("homeworkBindModel")HomeworkBindModel homeworkBindModel,
                                    BindingResult result,ModelAndView modelAndView,HttpSession session){
        if(session!=null && session.getAttribute("user")!=null) {
            List<String> exlist = this.exerciseService.getAllExercise();
            modelAndView.addObject("listex", exlist);
            modelAndView.addObject("homeworkBindModel", homeworkBindModel);
            modelAndView.setViewName("homework-add");
        }else {
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView postHomework(@Valid @ModelAttribute("homeworkBindModel")HomeworkBindModel homeworkBindModel,
                                     BindingResult result, ModelAndView modelAndView, HttpSession session,
                                     RedirectAttributes redirectAttributes){
        if(session!=null && session.getAttribute("user")!=null) {
            if (result.hasErrors()) {
                redirectAttributes.addFlashAttribute("homeworkBindModel", homeworkBindModel);
                modelAndView.setViewName("redirect:/homework/add");
            } else {
                if (exerciseService.getExerciseByName(homeworkBindModel.getExercise()).getDueDate().isBefore(LocalDateTime.now())) {
                    redirectAttributes.addFlashAttribute("youLate", true);
                    modelAndView.setViewName("redirect:/homework/add");
                } else {
                    this.homeworkService.addHomework(homeworkBindModel, (UserServiceModel) session.getAttribute("user"));
                    modelAndView.setViewName("redirect:/home");
                }
            }
        }else {
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }


    @GetMapping("/evaluation")
    public ModelAndView evaluateHomework(@Valid @ModelAttribute("scoreBindModel")ScoreBindModel scoreBindModel,
                                         BindingResult result,ModelAndView modelAndView, HttpSession session){

        if(session!=null && session.getAttribute("user")!=null) {
            HomeworkViewModel homework = this.homeworkService.getRandomHomework((Long) session.getAttribute("id"));
            UserViewModel user = this.userService.getUserDetailsById(homework.getAuthor().getId());
            modelAndView.addObject("git", user.getGit());
            session.setAttribute("hwid",homework.getId());
            modelAndView.setViewName("homework-check");
        }else{
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }

    @GetMapping("/error")
    public ModelAndView wrongComment(@Valid @ModelAttribute("scoreBindModel")ScoreBindModel scoreBindModel,
                                     BindingResult result,ModelAndView modelAndView, HttpSession session) {
        if (session != null && session.getAttribute("user") != null){
        modelAndView.addObject("scoreBindModel", scoreBindModel);
        modelAndView.setViewName("homework-check");
    }else {
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }

    @PostMapping("/evaluation")
    public ModelAndView scoreEvaluate(@Valid @ModelAttribute("scoreBindModel")ScoreBindModel scoreBindModel,
                                        BindingResult result,ModelAndView modelAndView,RedirectAttributes redirectAttributes,
                                        HttpSession session){
       if(session!=null && session.getAttribute("user")!=null) {
           if (result.hasErrors()) {
               redirectAttributes.addFlashAttribute("scoreBindModel", scoreBindModel);
               modelAndView.setViewName("redirect:/homework/error");
           } else {
               Long authorId = (Long) session.getAttribute("id");
               Long homeworkId = (Long) session.getAttribute("hwid");
               this.commentService.addNewComment(authorId,homeworkId,scoreBindModel);
               modelAndView.setViewName("redirect:/home");
           }
       }else{
           modelAndView.setViewName("redirect:/users/login");
       }

        return modelAndView;
    }

}
