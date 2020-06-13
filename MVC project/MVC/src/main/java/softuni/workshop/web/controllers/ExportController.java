package softuni.workshop.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;

@Controller
@RequestMapping("/export")
public class ExportController extends BaseController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }


    @GetMapping("/project-if-finished")
    public ModelAndView finishedProject() {
        ModelAndView modelAndView = new ModelAndView("/export/export-project-if-finished.html");
        String projectsIfFinished = projectService.exportFinishedProjects();
        modelAndView.addObject("projectsIfFinished", projectsIfFinished);
        return modelAndView;
    }

    @GetMapping("/employees-above")
    public ModelAndView employeeAbove() {
        ModelAndView modelAndView = new ModelAndView("/export/export-employees-with-age.html");
        String employeeAbove = employeeService.exportEmployeesWithAgeAbove();
        modelAndView.addObject("employeesAbove", employeeAbove);
        return modelAndView;
    }

}
