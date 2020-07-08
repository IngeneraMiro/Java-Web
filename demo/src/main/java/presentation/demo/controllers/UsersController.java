package presentation.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import presentation.demo.models.bindmodels.UserBindModel;
import presentation.demo.models.bindmodels.UserLogBindModel;
import presentation.demo.models.viewmodels.UserLogViewModel;
import presentation.demo.services.PracticeService;
import presentation.demo.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final PracticeService practiceService;

    @Autowired
    public UsersController(UserService userService, PracticeService practiceService) {
        this.userService = userService;
        this.practiceService = practiceService;
    }


    @GetMapping("")
    public String userHome(Model model,HttpSession session){

        return "patient-home";
    }


    @GetMapping("/register")
    public String register(Model model,HttpSession session,@AuthenticationPrincipal Principal principal){

//  ***  if visitor not admin - clear credentials and return to initial page  ***
        if(session.getAttribute("userType")==null || session.getAttribute("userType").equals("GUEST") || session.getAttribute("userType").equals("PATIENT")){
            if(session.getAttribute("userType").equals("GUEST") && this.userService.begin()){
//               ***  it is first call and you can proceed to register admin user  ***
            }else {
                session.setAttribute("userType", "GUEST");
                session.setAttribute("pName", "empty");
                return "redirect:/";
            }
        }
//  *** add bindModel to view model if not exist  ***
        if(!model.containsAttribute("userBindModel")){
            model.addAttribute("userBindModel",new UserBindModel());
            model.addAttribute("userExist",false);
            model.addAttribute("passwornotdmatch",false);

        }
//  ***  add to view model all active practices to choose from  ***
        model.addAttribute("practices",this.practiceService.getAllActivePractice());
        return "user-register";
    }

    @PostMapping("/register")
    public String regConfim(@Valid @ModelAttribute("userBindModel") UserBindModel userBindModel,
                            BindingResult result, RedirectAttributes redirectAttributes, HttpSession session,
                            @AuthenticationPrincipal Principal principal){

//  ***  if visitor not admin - clear credentials and return to initial page  ***
        if(session.getAttribute("userType")==null || session.getAttribute("userType").equals("GUEST") || session.getAttribute("userType").equals("PATIENT")){
           if(session.getAttribute("userType").equals("GUEST") && this.userService.begin()){
//               ***  it is first call and you can proceed to register admin user  ***
           }else {
               session.setAttribute("userType", "GUEST");
               session.setAttribute("pName", "empty");
               return "redirect:/";
           }
        }
//  ***  if there is not correct input data return to form  ***
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userBindModel",result);
            return "redirect:register";
        }
        try {
            this.userService.addUser(userBindModel);
        }catch (Exception e){

        }
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model,HttpSession session, RedirectAttributes redirectAttributes){

        //  *** add bindModel to view model if not exist  ***
        if(!model.containsAttribute("userLogBindModel")){
            model.addAttribute("userLogBindModel",new UserLogBindModel());
            model.addAttribute("notExist",false);
            session.setAttribute("userType","GUEST");
            session.setAttribute("pName","empty");
        }

//  ***  add to view model all active practices to choose from  ***
        model.addAttribute("practices",this.practiceService.getAllActivePractice());
        return "/user-login";
    }

    @PostMapping("/login")
    public String logConfirm(@Valid @ModelAttribute("userLogBindModel")UserLogBindModel userLogBindModel,
                             BindingResult result,RedirectAttributes redirectAttributes,HttpSession session){

//  ***  if there is not correct input data return to form  ***
        if(result.hasErrors()){
          redirectAttributes.addFlashAttribute("userLogBindModel",userLogBindModel);
          redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogBindModel",result);
          return "redirect:login";
        }

//  ***  try to login with provided credential ***
        UserLogViewModel user = this.userService.logUser(userLogBindModel);

//  ***  if login not success full return to form with appropriate message  ***
        if(user==null){
            redirectAttributes.addFlashAttribute("userLogBindModel",userLogBindModel);
            redirectAttributes.addFlashAttribute("notExist",true);
            return "redirect:login";
        }

//  ***  if login is success full attach type and name to session and return to appropriate page  ***
        session.setAttribute("userName",user.getFirstName());

           return "redirect:/admin/";

//           return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session,RedirectAttributes redirectAttributes){
        session.invalidate();

        return "redirect:/";
    }

}
