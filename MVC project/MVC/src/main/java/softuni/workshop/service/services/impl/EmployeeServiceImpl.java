package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import softuni.workshop.constants.GlobalConstants;
import softuni.workshop.data.dtos.EmployeeSeedDto;
import softuni.workshop.data.dtos.EmployeeSeedRootDto;
import softuni.workshop.data.entities.Employee;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.EmployeeRepository;
import softuni.workshop.service.services.EmployeeService;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ProjectService projectService;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ProjectService projectService, XmlParser xmlParser, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
        this.projectService = projectService;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
    }


    @Override
    @Transactional
    public void importEmployees() throws JAXBException, FileNotFoundException {
        EmployeeSeedRootDto employeeSeedRootDto = xmlParser.importFromXml(EmployeeSeedRootDto.class, GlobalConstants.XML_EMPLOYEE_PATH);
        for (EmployeeSeedDto e : employeeSeedRootDto.getEmployeeSeedDtoList()) {
            Employee employee = mapper.map(e, Employee.class);
            Project project = projectService.getProjectByName(employee.getProject().getName());
            employee.setProject(project);
            employeeRepository.save(employee);
        }
    }

    @Override
    public boolean areImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.XML_EMPLOYEE_PATH));
    }

    @Override
    @Transactional
    public String exportEmployeesWithAgeAbove() {
        StringBuilder sb = new StringBuilder();
        List<Employee> employees = employeeRepository.getEmployeesWithAgeAbove();
        for (Employee e : employees) {
            sb.append("Name: ").append(e.getFirstName()).append(" ").append(e.getLastName())
                    .append("\n\tAge: ").append(e.getAge()).append("\n\tProject name: ").
                    append(e.getProject().getName()).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
