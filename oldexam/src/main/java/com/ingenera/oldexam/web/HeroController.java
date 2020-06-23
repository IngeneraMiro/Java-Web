package com.ingenera.oldexam.web;

import com.ingenera.oldexam.models.bindingmodels.HeroBindModel;
import com.ingenera.oldexam.models.entities.HeroClass;
import com.ingenera.oldexam.models.viewmodels.HeroViewModel;
import com.ingenera.oldexam.services.HeroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/heroes")
public class HeroController {
    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/create")
    public String createHero(Model model, HttpSession session, RedirectAttributes redirectAttributes){

        if(session.getAttribute("userId")==null){
            return ("redirect:/users/login");
        }
        if(!model.containsAttribute("heroBindModel")){
            model.addAttribute("heroBindModel",new HeroBindModel());
        }
        List<HeroClass> list = Arrays.stream(HeroClass.values()).collect(Collectors.toList());
        model.addAttribute("classes",list);
        return "create-hero";
    }

    @PostMapping("/create")
    public String creationConfirm(@Valid @ModelAttribute("heroBindModel")HeroBindModel heroBindModel,
                                  BindingResult result,HttpSession session,RedirectAttributes redirectAttributes){
        if(session.getAttribute("userId")==null){
            return ("redirect:/users/login");
        }
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("heroBindModel",heroBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.heroBindModel",result);
            return "redirect:create";
        }
        try{
            this.heroService.addHero(heroBindModel);
        }catch (Error e){
            redirectAttributes.addFlashAttribute("heroBindModel",heroBindModel);
            return "redirect:create";
        }
        return "redirect:/home";
    }

    @GetMapping("/delete")
    public String removeHero(Model model,HttpSession session, @RequestParam("id")String id){
        if(session.getAttribute("userId")==null){
            return ("redirect:/users/login");
        }
        model.addAttribute("hero",this.heroService.getHeroById(id));

        return "/delete-hero";
    }

    @GetMapping("/confirmDelete/{id}")
    public String confirm(HttpSession session,@PathVariable("id")String id){
        if(session.getAttribute("userId")==null){
            return ("redirect:/users/login");
        }
        try{
            this.heroService.removeHeroById(id);
        }catch (Error e){
            return "redirect:/home";
        }
        return "redirect:/home";
    }

    @GetMapping("/details")
    public String details(Model model, @RequestParam("id") String id, HttpSession session){
        if(session.getAttribute("userId")==null){
            return ("redirect:/users/login");
        }
        model.addAttribute("hero",this.heroService.getHeroById(id));
        return "details-hero";
    }

}
