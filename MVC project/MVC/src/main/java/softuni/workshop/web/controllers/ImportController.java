package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {
    private final CompanyService companyService;
    private final EmployeeService employeeService;
    private final ProjectService projectService;

    @Autowired
    public ImportController(CompanyService companyService, EmployeeService employeeService, ProjectService projectService) {
        this.companyService = companyService;
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping("/xml")
    public ModelAndView xmls(){

        boolean[] areImported = new boolean[]{companyService.areImported(),projectService.areImported(),employeeService.areImported()};
        ModelAndView modelAndView = new ModelAndView("xml/import-xml");
        modelAndView.addObject("areImported", areImported);
        return modelAndView;
    }

    @GetMapping("/companies")
    public ModelAndView companies() throws IOException {
        String text = companyService.readCompaniesXmlFile();
        ModelAndView modelAndView = new ModelAndView("xml/import-companies");
        modelAndView.addObject("companies",text);
        return modelAndView;
    }

    @PostMapping("/companies")
    public ModelAndView companyConform() throws JAXBException, FileNotFoundException {
        companyService.importCompanies();
        return this.redirect("/import/xml");
    }

    @GetMapping("/employees")
    public ModelAndView employees() throws IOException {

        String text = employeeService.readEmployeesXmlFile();
        ModelAndView modelAndView = new ModelAndView("xml/import-employees");
        modelAndView.addObject("employees",text);
        return modelAndView;
    }

    @PostMapping("/employees")
    public ModelAndView employeeConform() throws JAXBException, FileNotFoundException {
        employeeService.importEmployees();
        return this.redirect("/import/xml");
    }

    @GetMapping("/projects")
    public ModelAndView projects() throws IOException {
        String text = projectService.readProjectsXmlFile();
        ModelAndView modelAndView = new ModelAndView("xml/import-projects");
        modelAndView.addObject("projects",text);
        return modelAndView;
    }

    @PostMapping("/projects")
    public ModelAndView projectConform() throws JAXBException, FileNotFoundException {
        projectService.importProjects();
        return this.redirect("/import/xml");
    }
}
