package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

@Controller
public class HomeController extends BaseController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public HomeController(CompanyService companyService, EmployeeService employeeService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    //    @RequestMapping(value = "/",method = RequestMethod.GET)
    @GetMapping(value = "/")
    public ModelAndView Index(){
        return new ModelAndView("index");
    }

    @GetMapping(value = "/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("home");
        boolean areImported = companyService.areImported() && employeeService.areImported() && projectService.areImported();
        modelAndView.addObject("areImported",areImported);
        return modelAndView;
    }
}
