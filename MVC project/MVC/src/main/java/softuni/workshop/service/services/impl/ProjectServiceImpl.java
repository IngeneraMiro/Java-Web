package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.GlobalConstants;
import softuni.workshop.data.dtos.ProjectDto;
import softuni.workshop.data.dtos.ProjectSeedRootDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.ProjectRepository;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.service.services.ProjectService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final CompanyService companyService;
    private final XmlParser xmlParser;
    private final ModelMapper mapper;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, CompanyService companyService, XmlParser xmlParser, ModelMapper mapper) {
        this.projectRepository = projectRepository;
        this.companyService = companyService;
        this.xmlParser = xmlParser;
        this.mapper = mapper;
    }

    @Override
    public void importProjects() throws JAXBException, FileNotFoundException {
        ProjectSeedRootDto projectSeedRootDto = xmlParser.importFromXml(ProjectSeedRootDto.class,GlobalConstants.XML_PROJECT_PATH);
        for (ProjectDto p: projectSeedRootDto.getProjectDtos()){
            Project project = mapper.map(p,Project.class);
            Company company = companyService.getCompanyByName(project.getCompany().getName());
            project.setCompany(company);
            projectRepository.save(project);
        }

    }

    @Override
    public boolean areImported() {
        return projectRepository.count()>0;
    }

    @Override
    public String readProjectsXmlFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.XML_PROJECT_PATH));
    }

    @Override
    public Project getProjectByName(String name) {
        return projectRepository.findByName(name);
    }

    @Override
    public String exportFinishedProjects(){
        StringBuilder sb = new StringBuilder();
        List<Project> projects = projectRepository.getFinishedProjects();
        for (Project p: projects){
            sb.append("Project name: ").append(p.getName()).append("\n\tDescription: ")
               .append(p.getDescription()).append("\n\t").append(String.format("%.2f",p.getPayment()))
                    .append(System.lineSeparator());
        }


        return sb.toString();
    }
}
