package softuni.workshop.service.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.workshop.constants.GlobalConstants;
import softuni.workshop.data.dtos.CompanySeedRootDto;
import softuni.workshop.data.entities.Company;
import softuni.workshop.data.entities.Project;
import softuni.workshop.data.repositories.CompanyRepository;
import softuni.workshop.service.services.CompanyService;
import softuni.workshop.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper mapper;
    private final XmlParser xmlParser;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper mapper, XmlParser xmlParser) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public void importCompanies() throws JAXBException, FileNotFoundException {
        CompanySeedRootDto companySeedRootDto = xmlParser.importFromXml(CompanySeedRootDto.class, GlobalConstants.XML_COMPANIES_PATH);
        companySeedRootDto.getCompanySeedDtos().stream().map(c -> new Company(c.getName(), new ArrayList<Project>())).
                forEach(companyRepository::save);
    }

    @Override
    public boolean areImported() {
        return companyRepository.count() > 0;
    }

    @Override
    public String readCompaniesXmlFile() throws IOException {
        return Files.readString(Path.of(GlobalConstants.XML_COMPANIES_PATH));
    }

    @Override
    public Company getCompanyByName(String name) {
        return companyRepository.findByName(name);
    }
}
