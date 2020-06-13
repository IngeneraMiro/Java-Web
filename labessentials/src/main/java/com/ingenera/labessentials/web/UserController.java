package com.ingenera.labessentials.web;

import com.ingenera.labessentials.models.bindmodels.UserBindModel;
import com.ingenera.labessentials.models.bindmodels.UserLoginBindModel;
import com.ingenera.labessentials.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public ModelAndView registerUser(@ModelAttribute("newUser")UserBindModel userBindModel,ModelAndView modelAndView){
        modelAndView.setViewName("auth-register");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView userLogin(@ModelAttribute("userlog")UserLoginBindModel userLoginBindModel, ModelAndView modelAndView){
        modelAndView.setViewName("auth-login");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView logUser(@ModelAttribute("userlog")UserLoginBindModel userLoginBindModel, BindingResult result,
                                HttpSession session, ModelAndView modelAndView, HttpServletRequest request){
        if(result.hasErrors()){

            modelAndView.setViewName("auth-login");
        }else{
          if(this.userService.checkUser(userLoginBindModel)==null){
              result.addError(new ObjectError("username","Bad username or password") );
              modelAndView.setViewName("auth-login");
          }else {
              modelAndView.setViewName("offers");
              session.setAttribute("name",userLoginBindModel.getUsername());
          }

        }
        request.getSession().setAttribute("name",userLoginBindModel.getUsername());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView saveUser(@Valid @ModelAttribute("newUser")UserBindModel userBindModel, BindingResult result, ModelAndView modelAndView){
         if(result.hasErrors()){
//             log.error("Error registering user: {}", result.getAllErrors());
             modelAndView.setViewName("auth-register");
         }else{
             try {
                 this.userService.saveUser(userBindModel);
                 modelAndView.setViewName("auth-login");
             }catch (IllegalArgumentException e){
//                 log.error("Error registering User", e);
                 modelAndView.setViewName("auth-register");
             }
         }

        return modelAndView;
    }



}
