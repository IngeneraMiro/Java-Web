package com.ingenera.demo.web;

import com.ingenera.demo.models.bindmodels.UserLoginBindeingModel;
import com.ingenera.demo.models.bindmodels.UserRegisterBindingModel;
import com.ingenera.demo.models.servicemodels.UserServiceModel;
import com.ingenera.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserLoginController {
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserLoginController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String userLogin(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginConform(@Valid @ModelAttribute("userLoginBindeingModel")
                                                 UserLoginBindeingModel userLoginBindeingModel, BindingResult bindingResult,
                                     ModelAndView modelAndView, HttpSession httpSession){
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/users/login");
        }else{
            modelAndView.setViewName("redirect:/home");
        }
       //todo login in user service
       httpSession.setAttribute("user","userServiceModel");
       httpSession.setAttribute("id","userId");
       return modelAndView;
    }

    @GetMapping("/register")
    public String userRegister(){
        return "/register";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                             UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                                        ModelAndView modelAndView){
       if(bindingResult.hasErrors()){
           modelAndView.setViewName("redirect:/users/register");
       }else{
        UserServiceModel userServiceModel =  this.userService.registerUser(mapper.map(userRegisterBindingModel, UserServiceModel.class));
           modelAndView.setViewName("redirect:/users/login");
       }

       return modelAndView;
    }

}
