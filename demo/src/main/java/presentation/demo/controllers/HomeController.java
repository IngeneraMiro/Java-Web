package presentation.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import presentation.demo.services.PracticeService;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final PracticeService practiceService;

    public HomeController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }


    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal Principal principal, HttpSession session){
        model.addAttribute("user",principal);
        List<String> practices = this.practiceService.getAllActivePractice();
        model.addAttribute("practices",practices);
        session.setAttribute("pName","empty");
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal Principal principal){

        return "home";
    }

    @GetMapping("/admin")
    public String admin(Model model, @AuthenticationPrincipal Principal principal){

        return "admin";
    }

}
