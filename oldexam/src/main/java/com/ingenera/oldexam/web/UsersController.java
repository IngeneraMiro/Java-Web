package com.ingenera.oldexam.web;

import com.ingenera.oldexam.models.bindingmodels.UserBindModel;
import com.ingenera.oldexam.models.bindingmodels.UserLogModel;
import com.ingenera.oldexam.models.entities.User;
import com.ingenera.oldexam.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(Model model,RedirectAttributes redirectAttributes, HttpSession session){
        if(!model.containsAttribute("userBindModel")){
            model.addAttribute("userBindModel", new UserBindModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String regConform(@Valid @ModelAttribute("userBindModel")UserBindModel userBindModel,
                             BindingResult result, HttpSession session, RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userBindModel",result);
            return "redirect:register";
        }
        try {
            this.userService.addUser(userBindModel);
        }catch (Error e){
            redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
            return "redirect:register";
        }
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model,RedirectAttributes redirectAttributes,HttpSession session){
       if(!model.containsAttribute("userLogModel")){
           model.addAttribute("userLogModel",new UserLogModel());
       }
        return "login";
    }

    @PostMapping("/login")
    public String logConfirm(@Valid @ModelAttribute("userLogModel")UserLogModel userLogModel,
                             BindingResult result,HttpSession session,RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userLogModel",userLogModel);
            return "redirect:login";
        }
        try{
            User user = this.userService.getLoggedUser(userLogModel.getUsername(),userLogModel.getPassword());
            session.setAttribute("userId",user.getId());
            session.setAttribute("name",user.getUsername());
        }catch (Error e){
            redirectAttributes.addFlashAttribute("userLogModel",userLogModel);
            return "redirect:login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }


}
