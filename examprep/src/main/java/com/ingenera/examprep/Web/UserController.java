package com.ingenera.examprep.Web;

import com.ingenera.examprep.models.bindmodels.UserBindModel;
import com.ingenera.examprep.models.bindmodels.UserLogModel;
import com.ingenera.examprep.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.LocalDateTime;

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
    public String logUser(Model model, RedirectAttributes redirectAttributes,HttpSession session){
        if(!model.containsAttribute("userLogModel")){
            model.addAttribute("userLogModel",new UserLogModel());
            model.addAttribute("fail",false);
            if(session.getAttribute("ban")==null) {
                model.addAttribute("ban", false);
            }
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConform(@Valid @ModelAttribute("userLogModel")UserLogModel userLogModel,
                               BindingResult result,RedirectAttributes redirectAttributes,
                               HttpSession session){

        if(session.getAttribute("time")!=null && LocalDateTime.now().isBefore((LocalDateTime) session.getAttribute("time"))){
            redirectAttributes.addFlashAttribute("ban",true);
            redirectAttributes.addFlashAttribute("userLogModel",userLogModel);
            return "redirect:login";
        }else {
            redirectAttributes.addFlashAttribute("ban",false);
        }

         if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userLogModel",userLogModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogModel",result);
            return "redirect:login";
        }
        try {
            String logged = this.userService.getUserByUsernameAndPass(userLogModel.getUsername(), userLogModel.getPassword());
            session.setAttribute("userId",logged);
        }catch (Exception e){
            if(session.getAttribute("numFails")==null){
                session.setAttribute("numFails",1);
            }else {
                int num = (int) session.getAttribute("numFails");
                session.setAttribute("numFails",num+1);
            }
            if((int)session.getAttribute("numFails")==3){
                redirectAttributes.addFlashAttribute("userLogModel",new UserLogModel());
                redirectAttributes.addFlashAttribute("ban",true);
                redirectAttributes.addFlashAttribute("fail",false);
                session.setAttribute("numFails",0);
                session.setAttribute("time",LocalDateTime.now().plusMinutes(3));
                return "redirect:login";
            }
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
