package com.ingenera.linkeduot.services.seviceimpl;

import com.ingenera.linkeduot.models.bindmodels.EmployeeBindModel;
import com.ingenera.linkeduot.models.entities.Employee;
import com.ingenera.linkeduot.models.viewmodels.EmployeeDetailsModel;
import com.ingenera.linkeduot.models.viewmodels.EmployeeViewModel;
import com.ingenera.linkeduot.repositories.EmployeeRepository;
import com.ingenera.linkeduot.services.CompanyService;
import com.ingenera.linkeduot.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final CompanyService companyService;
    private final ModelMapper mapper;

 @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyService companyService, ModelMapper mapper) {
        this.employeeRepository = employeeRepository;
     this.companyService = companyService;
     this.mapper = mapper;
    }


    @Override
    public Employee addEmployee(EmployeeBindModel model) {
        Employee employee = mapper.map(model,Employee.class);
        employee.setCompany(this.companyService.getByName(model.getCompany()));

        return this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    @Transactional
    public List<EmployeeViewModel> getAllViewEmpl() {
        List<Employee> list = this.employeeRepository.findAll();
        return list.stream().map(e->this.mapper.map(e,EmployeeViewModel.class)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDetailsModel getEmployeeById(Long id) {
        Employee employee = this.employeeRepository.getById(id);
        EmployeeDetailsModel model = this.mapper.map(employee,EmployeeDetailsModel.class);
        model.setCompany(employee.getCompany().getName());
        return model;
    }

    @Override
    public void removeEmployee(Long id) {
        this.employeeRepository.deleteById(id);
    }
}
