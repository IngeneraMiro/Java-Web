package com.ingenera.linkeduot.controllers;

import com.ingenera.linkeduot.models.bindmodels.CompanyBindModel;
import com.ingenera.linkeduot.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/add")
    public String addCompany(Model model,RedirectAttributes redirectAttributes){
        System.out.println();
        if(!model.containsAttribute("companyBindModel")){
            model.addAttribute("companyBindModel",new CompanyBindModel());
        }
        return "company-add";
    }

    @PostMapping("/addField")
    public String CompanyAddConfirm(@Valid @ModelAttribute("companyBindModel")CompanyBindModel companyBindModel,
                                    BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyBindModel",result);
            redirectAttributes.addFlashAttribute("companyBindModel",companyBindModel);
            return "redirect:/companies/add";
        }
        try{
            this.companyService.addCompany(companyBindModel);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("invalid",e.getMessage());
            redirectAttributes.addFlashAttribute("companyBindModel",companyBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.companyBindModel",result);
            return "redirect:/companies/add";
        }
        return "index";
    }

    @GetMapping("/all")
    public String getAllCompanies(Model model){
       model.addAttribute("companies",this.companyService.getAllCompanyInfo());
        return "company-all";
    }

    @GetMapping("/delete/{id}")
    public String removeCompany(@PathVariable("id")Long id){
        this.companyService.deleteCompany(id);
        return ("index");
    }

}
