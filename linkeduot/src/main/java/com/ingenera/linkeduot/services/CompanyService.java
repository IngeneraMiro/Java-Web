package com.ingenera.linkeduot.services;

import com.ingenera.linkeduot.models.bindmodels.CompanyBindModel;
import com.ingenera.linkeduot.models.entities.Company;

import java.util.List;

public interface CompanyService {

   Company addCompany(CompanyBindModel companyBindModel);
   List<String> getAllCompanies();
   Company getByName(String name);
}
