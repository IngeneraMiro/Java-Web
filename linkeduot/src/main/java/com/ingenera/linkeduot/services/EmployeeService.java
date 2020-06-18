package com.ingenera.linkeduot.services;

import com.ingenera.linkeduot.models.bindmodels.EmployeeBindModel;
import com.ingenera.linkeduot.models.entities.Employee;

public interface EmployeeService {

    Employee addEmployee(EmployeeBindModel model);

}
