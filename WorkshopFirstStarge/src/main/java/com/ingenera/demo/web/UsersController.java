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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UsersController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/login")
    public String userLogin(@ModelAttribute("model")UserLoginBindeingModel userLoginBindeingModel) {
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginConform(@Valid @ModelAttribute("model")
                                             UserLoginBindeingModel userLoginBindeingModel, BindingResult bindingResult,
                                     ModelAndView modelAndView, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("login");
        } else {
            UserServiceModel model = this.userService.getUserByName(userLoginBindeingModel.getUsername());
            if(model==null || !model.getPassword().equals(userLoginBindeingModel.getPassword()) ){

            }

            httpSession.setAttribute("user", "userServiceModel");
            httpSession.setAttribute("id", "userId");
            modelAndView.setViewName("redirect:/home");
        }
        //todo login in user service

        return modelAndView;
    }

    @GetMapping("/register")
    public String userRegister(@ModelAttribute("userRegisterBindingModel")
                                           UserRegisterBindingModel userRegisterBindingModel) {
        return "/register";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel")
                                                UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                                        ModelAndView modelAndView,RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(userRegisterBindingModel);
            modelAndView.setViewName("redirect:/users/register");
        } else {
            UserServiceModel userServiceModel = this.userService.registerUser(mapper.map(userRegisterBindingModel, UserServiceModel.class));
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

}
