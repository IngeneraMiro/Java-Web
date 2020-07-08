package presentation.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import presentation.demo.models.entities.Authority;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collection;

@Controller
public class BaseController {

    @PostMapping("/redirect")
    public String redirect(Model model, @AuthenticationPrincipal Principal principal, HttpSession session){

        Collection<SimpleGrantedAuthority> set = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if(set.contains("ROLE_ADMIN")){
            return "admin";
        }

        return "home";
    }

}
