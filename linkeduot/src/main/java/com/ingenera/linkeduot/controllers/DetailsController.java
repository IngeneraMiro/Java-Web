package com.ingenera.linkeduot.controllers;

import com.ingenera.linkeduot.services.CompanyService;
import com.ingenera.linkeduot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/details")
public class DetailsController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public DetailsController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }

    @GetMapping("/company/{name}")
    public String getCompanyDetails(Model model, @PathVariable("name") String name){
        model.addAttribute("details",this.companyService.getViewByName(name));
        return "company-details";
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeDetails(Model model,@PathVariable("id")Long id){

        model.addAttribute("employee",this.employeeService.getEmployeeById(id));
        return "employee-details";
    }

}
