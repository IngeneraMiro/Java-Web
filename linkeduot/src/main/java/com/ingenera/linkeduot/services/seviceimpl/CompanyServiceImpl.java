package com.ingenera.linkeduot.services.seviceimpl;

import com.ingenera.linkeduot.models.bindmodels.CompanyBindModel;
import com.ingenera.linkeduot.models.entities.Company;
import com.ingenera.linkeduot.models.viewmodels.CompanyDetailsModel;
import com.ingenera.linkeduot.models.viewmodels.CompanyViewModel;
import com.ingenera.linkeduot.repositories.CompanyRepository;
import com.ingenera.linkeduot.services.CompanyService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    public final CompanyRepository companyRepository;
    public final ModelMapper mapper;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ModelMapper mapper) {
        this.companyRepository = companyRepository;
        this.mapper = mapper;
    }

    @Override
    public Company addCompany(CompanyBindModel companyBindModel) {
        Company company = this.mapper.map(companyBindModel, Company.class);
        return this.companyRepository.saveAndFlush(company);
    }

    @Override
    public List<String> getAllCompanies() {
        return this.companyRepository.findAll().stream().map(Company::getName).collect(Collectors.toList());
    }

    @Override
    public Company getByName(String name) {
        return this.companyRepository.getByName(name);
    }

    @Override
    public List<CompanyViewModel> getAllCompanyInfo() {
        return this.companyRepository.findAll().stream().
                map(c -> this.mapper.map(c, CompanyViewModel.class)).map(s -> {
            if (s.getDescription().length() <= 5) {
                return s;
            } else {
                s.setDescription(s.getDescription().substring(0, 4));
                return s;
            }
        }).collect(Collectors.toList());
    }

    @Override
    public CompanyDetailsModel getViewByName(String name) {
        return this.mapper.map(this.companyRepository.getByName(name),CompanyDetailsModel.class);
    }
}
