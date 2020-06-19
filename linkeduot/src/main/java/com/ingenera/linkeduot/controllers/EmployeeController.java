package com.ingenera.linkeduot.controllers;

import com.ingenera.linkeduot.models.bindmodels.EmployeeBindModel;
import com.ingenera.linkeduot.models.viewmodels.EmployeeViewModel;
import com.ingenera.linkeduot.services.CompanyService;
import com.ingenera.linkeduot.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(CompanyService companyService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
    }


    @GetMapping("/add")
    public String addEmployee(Model model,RedirectAttributes redirectAttributes){

        if(!model.containsAttribute("employeeBindModel")){
            model.addAttribute("employeeBindModel",new EmployeeBindModel());
        }
        model.addAttribute("companies",this.companyService.getAllCompanies());
        return "employee-add";
    }


    @PostMapping("/addEmpl")
    public String getAllEmployees(@Valid @ModelAttribute("employeeBindModel")EmployeeBindModel employeeBindModel,
                                  BindingResult result, RedirectAttributes redirectAttributes){

        if(result.hasErrors()){
            redirectAttributes.addFlashAttribute("employeeBindModel",employeeBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeBindModel",result);
            return "redirect:/employees/add";
        }
        this.employeeService.addEmployee(employeeBindModel);
        return ("index");
    }


    @GetMapping("/all")
    public String getAllEmployees(Model model){
        List<EmployeeViewModel> models = this.employeeService.getAllViewEmpl();
        model.addAttribute("models", models);
        return "employee-all";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id")Long id){
        this.employeeService.removeEmployee(id);
        return "/index";
    }

}
