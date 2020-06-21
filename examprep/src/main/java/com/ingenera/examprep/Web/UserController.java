package com.ingenera.examprep.Web;

import com.ingenera.examprep.models.bindmodels.UserBindModel;
import com.ingenera.examprep.models.bindmodels.UserLogModel;
import com.ingenera.examprep.models.entities.User;
import com.ingenera.examprep.services.UserService;
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
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser(Model model, RedirectAttributes redirectAttributes){

        if(!model.containsAttribute("userBindModel")){
            model.addAttribute("userBindModel", new UserBindModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public String regUserConform(@Valid @ModelAttribute("userBindModel") UserBindModel userBindModel,
                                 BindingResult result,RedirectAttributes redirectAttributes){
        if(result.hasErrors() || !userBindModel.getPassword().equals(userBindModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userBindModel",result);
            redirectAttributes.addFlashAttribute("match",userBindModel.getPassword().equals(userBindModel.getConfirmPassword()));
            return "redirect:register";
        }
        try {
           String userId =  this.userService.addUser(userBindModel);

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("invalid",true);

        }
        return "redirect:login";
    }

    @GetMapping("/login")
    public String logUser(Model model, RedirectAttributes redirectAttributes){
        if(!model.containsAttribute("userLogModel")){
            model.addAttribute("userLogModel",new UserLogModel());
            model.addAttribute("fail",false);
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConform(@Valid @ModelAttribute("userLogModel")UserLogModel userLogModel,
                               BindingResult result,RedirectAttributes redirectAttributes,
                               HttpSession session){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userLogModel",userLogModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogModel",result);
            return "redirect:login";
        }
        try {
            String logged = this.userService.getUserByUsernameAndPass(userLogModel.getUsername(), userLogModel.getPassword());
            session.setAttribute("userId",logged);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("userLogModel",userLogModel);
            redirectAttributes.addFlashAttribute("fail",true);
            return "redirect:login";
        }

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "index";
    }

}
