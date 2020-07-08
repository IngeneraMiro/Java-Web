package presentation.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import presentation.demo.models.bindmodels.PracticeBindModel;
import presentation.demo.models.viewmodels.PracticeDetailsModel;
import presentation.demo.models.viewmodels.PracticeViewModel;
import presentation.demo.services.PracticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/practices")
public class PracticesController {
    private final PracticeService practiceService;

    @Autowired
    public PracticesController(PracticeService practiceService) {
        this.practiceService = practiceService;
    }


    @GetMapping("/add")
    public String addPractice(Model model,RedirectAttributes redirectAttributes,@RequestParam("type")String type){
        if(!model.containsAttribute("practiceBindModel")){
            model.addAttribute("practiceBindModel",new PracticeBindModel());
        }
        model.addAttribute("userType",type);
        model.addAttribute("pName","empty");
        if(!type.equals("ADMIN")){
            return "redirect:/users/login";
        }

        model.addAttribute("practices",practiceService.getAllPractices());
        return "practice-add";
    }

    @PostMapping("/add")
    public String addPracticeConfirm(@Valid @ModelAttribute("practiceBindModel")PracticeBindModel practiceBindModel,
                                     BindingResult result, RedirectAttributes redirectAttributes, HttpSession session){
        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("practiceBindModel",practiceBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.practiceBindModel",result);
            return "redirect:add?type=ADMIN";
        }

        try{
            PracticeViewModel practice =  this.practiceService.addPractice(practiceBindModel);
        }catch (Error e){

        }
        redirectAttributes.addFlashAttribute("userType","ADMIN");
        redirectAttributes.addFlashAttribute("pName","empty");
        return "redirect:/";
    }

    @GetMapping("/details")
    public String detail(Model model,@RequestParam(value = "pName")String pName,
                         HttpSession session){
        if(!session.getAttribute("userType").equals("ADMIN")){
            session.setAttribute("pName","empty");
            session.setAttribute("userType","GUEST");
            return "redirect:/";
        }

        if(model.containsAttribute("pName")){
            pName =(String)model.getAttribute("pName");
        }
       try {
           PracticeDetailsModel practice = this.practiceService.getPracticeByName(pName);
           model.addAttribute("practice",practice);
       }catch (Exception e){
             return ("redirect:/");
       }
        session.setAttribute("pName",pName);
        model.addAttribute("func",false);
       if(session.getAttribute("userType").equals("ADMIN")){
           model.addAttribute("func",true);
       }

      return "practice-details";
    }

    @PostMapping("/edit")
    public String edit(RedirectAttributes redirectAttributes,@RequestParam(value = "action")String action,
                       HttpSession session){
        if(!session.getAttribute("userType").equals("ADMIN")){
            session.setAttribute("pName","empty");
            session.setAttribute("userType","GUEST");
            session.setAttribute("userName","");
            return "redirect:/";
        }
        switch (action){
            case "deactivate":
                this.practiceService.deactivate((String)session.getAttribute("pName"));
                break;
            case "activate":
                this.practiceService.activate((String)session.getAttribute("pName"));
                break;
            case "edit":
                return  "redirect:details";
            default:
                break;
        }

//        String ret = String.format("redirect:details?pName=%s",session.getAttribute("pName"));
        redirectAttributes.addFlashAttribute("pName",session.getAttribute("pName"));
        return "redirect:details?pName=d";
    }


}
