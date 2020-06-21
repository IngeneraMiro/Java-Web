package com.ingenera.examprep.Web;

import com.ingenera.examprep.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    private final ItemService itemService;

    @Autowired
    public HomeController(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/")
    public String indexPage(){

        return ("index");
    }

    @GetMapping("/home")
    public String getHome(Model model, HttpSession session){
        if(session.getAttribute("userId")!=null) {
            model.addAttribute("counter",this.itemService.countItems());

            return "home";
        }
        return "redirect:/user/login";
    }
}
