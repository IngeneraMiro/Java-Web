package web.exam.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.exam.models.bindmodels.UserBindModel;
import web.exam.models.bindmodels.UserLogBindModel;
import web.exam.models.entities.Product;
import web.exam.models.entities.User;
import web.exam.services.ProductService;
import web.exam.services.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public UsersController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/register")
    public String register(Model model, RedirectAttributes redirectAttributes){

       if(!model.containsAttribute("userBindModel")){
           UserBindModel model1 = new UserBindModel();
           model.addAttribute("userBindModel",model1);
       }

       return "register";
   }

   @PostMapping("register")
    public String regConfirm(@Valid @ModelAttribute("userBindModel")UserBindModel userBindModel,
                             BindingResult result, RedirectAttributes redirectAttributes){
       if(result.hasErrors()){
           redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userBindModel",result);
           return "redirect:register";
       }
       if(!userBindModel.getPassword().equals(userBindModel.getConfirmPassword())){
           result.rejectValue("password", "error.userRegisterBindingModel", "Password did not match");
           redirectAttributes.addFlashAttribute("userBindModel",userBindModel);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userBindModel",result);

           return "redirect:register";
       }

       try{
         User user =  this.userService.addUser(userBindModel);
       }catch (Exception e){
           return "redirect:register";
       }
       return "redirect:login";
   }

   @GetMapping("/login")
    public String login(Model model,RedirectAttributes redirectAttributes){

        if(!model.containsAttribute("userLogBindModel")){
            model.addAttribute("userLogBindModel",new UserLogBindModel());
            redirectAttributes.addFlashAttribute("noUser",false);
        }

       return "login";
   }

   @PostMapping("/login")
    public String logConfirm(@Valid @ModelAttribute("userLogBindModel")UserLogBindModel userLogBindModel,
                             BindingResult result,HttpSession session,
                             RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("userLogBindModel",userLogBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLogBindModel",result);
             return "redirect:login";
        }
        User user = this.userService.logUser(userLogBindModel.getUsername(),userLogBindModel.getPassword());
        if(user == null){
            redirectAttributes.addFlashAttribute("userLogBindModel",userLogBindModel);
            redirectAttributes.addFlashAttribute("noUser",true);
            return "redirect:login";
        }
        session.setAttribute("userId",user.getId());
        session.setAttribute("items",new HashSet<Product>());
        return "redirect:/home";
   }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

    @GetMapping("/buy/{id}")
    public String buyProduct(HttpSession session, @PathVariable("id")String id){
        if(session.getAttribute("userId")==null){
            return "redirect:/users/login";
        }
        if(session.getAttribute("items")==null){
            session.setAttribute("items",new HashSet<Product>());
        }
        Set<Product> set = (Set<Product>) session.getAttribute("items");
        set.add(this.productService.getById(id));
        session.setAttribute("items",set);
        return "redirect:/home";
    }
    @GetMapping("/purchase")
    public String purchase(HttpSession session){
        if(session.getAttribute("userId")==null){
            return "redirect:/users/login";
        }
        session.setAttribute("items",new HashSet<Product>());
        return "redirect:/home";
    }

}
