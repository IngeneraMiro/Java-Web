package com.ingenera.springworkshop.web;

import com.ingenera.springworkshop.models.bindmodels.UserBindModel;
import com.ingenera.springworkshop.models.bindmodels.UserLogBindModel;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.models.viewmodels.UserViewModel;
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

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public ModelAndView userRegister(@Valid @ModelAttribute("userBindModel")UserBindModel userBindModel,
                                     BindingResult result,ModelAndView modelAndView){
        System.out.println();
        modelAndView.addObject("userBindModel",userBindModel);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
   public ModelAndView registerConfirm(@Valid @ModelAttribute("userBindModel")UserBindModel userBindModel,BindingResult result,
                                       ModelAndView modelAndView,HttpSession session,RedirectAttributes redirectAttributes){
        System.out.println();
        if(result.hasErrors()){
           redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
           modelAndView.setViewName("redirect:/users/register");
        }else{
            if(!userBindModel.getPassword().equals(userBindModel.getConfirmPassword())){
                redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
                redirectAttributes.addFlashAttribute("passwornotdmatch",true);
                modelAndView.setViewName("redirect:/users/register");
            }else {
                if(userService.checkUsername(userBindModel.getUsername())){
                    redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
                    redirectAttributes.addFlashAttribute("userexist",true);
                    modelAndView.setViewName("redirect:/users/register");
                }else {
                    userService.registerUser(userBindModel);
                    modelAndView.setViewName("redirect:/users/login");
                }
            }
        }

        return modelAndView;
    }




    @GetMapping("/login")
    public ModelAndView userLogin(@Valid @ModelAttribute("userLogBindModel")UserLogBindModel userLogBindModel,
                                  BindingResult result, ModelAndView modelAndView){
        modelAndView.addObject("userLogBindModel",userLogBindModel);
        modelAndView.setViewName("login");
        return modelAndView;
    }

   @PostMapping("/login")
    public ModelAndView logConfirm(@Valid @ModelAttribute("userLogBindModel")UserLogBindModel userLogBindModel,
                                   BindingResult result, ModelAndView modelAndView, HttpSession session,
                                   RedirectAttributes redirectAttributes){
       System.out.println();
       if(result.hasErrors()){
           redirectAttributes.addFlashAttribute("userLogBindModel",userLogBindModel);
           modelAndView.setViewName("redirect:/users/login");
       }else{
           UserServiceModel userServiceModel = userService.getServiceModel(userLogBindModel.getUsername());
           if(userServiceModel==null || !userServiceModel.getPassword().equals(userLogBindModel.getPassword())){
               redirectAttributes.addFlashAttribute("notfound",true);
               modelAndView.setViewName("redirect:/users/login");
           }else {
               session.setAttribute("user",userServiceModel);
               session.setAttribute("id",userServiceModel.getId());
               session.setAttribute("role",userServiceModel.getRole().getName());
               modelAndView.setViewName("redirect:/home");
           }
       }
       return modelAndView;
   }

   @GetMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpSession session){
        session.invalidate();
        modelAndView.setViewName("index");
        return modelAndView;
   }

   @GetMapping("/profile")
    public ModelAndView userDetails(ModelAndView modelAndView,HttpSession session){
        UserServiceModel userServiceModel = (UserServiceModel) session.getAttribute("user");
        UserViewModel user = this.userService.getUserDetails(userServiceModel.getUsername());
       modelAndView.addObject("user",user);
       modelAndView.setViewName("profile");
       return modelAndView;
   }

}
