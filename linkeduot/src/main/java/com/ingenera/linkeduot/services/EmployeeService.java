package com.ingenera.linkeduot.services;

import com.ingenera.linkeduot.models.bindmodels.EmployeeBindModel;
import com.ingenera.linkeduot.models.entities.Employee;
import com.ingenera.linkeduot.models.viewmodels.EmployeeDetailsModel;
import com.ingenera.linkeduot.models.viewmodels.EmployeeViewModel;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(EmployeeBindModel model);

    List<EmployeeViewModel>  getAllViewEmpl();

    EmployeeDetailsModel getEmployeeById(Long id);

    void removeEmployee(Long id);
}
