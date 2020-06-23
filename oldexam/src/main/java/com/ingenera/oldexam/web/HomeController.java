package com.ingenera.oldexam.web;

import com.ingenera.oldexam.models.viewmodels.HeroViewModel;
import com.ingenera.oldexam.services.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    private final HeroService heroService;

    @Autowired
    public HomeController(HeroService heroService) {
        this.heroService = heroService;
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/users/login";
        }
        List<HeroViewModel> list = this.heroService.getAllHeroes();
        model.addAttribute("heroes",list);
        return "home";
    }

}
