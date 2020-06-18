package com.ingenera.linkeduot.controllers;

import com.ingenera.linkeduot.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/details")
public class DetailsController {
    private final CompanyService companyService;

    @Autowired
    public DetailsController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company/{name}")
    public String getCompanyDetails(Model model, @PathVariable("name") String name){
        model.addAttribute("details",this.companyService.getViewByName(name));
        return "company-details";
    }


}
