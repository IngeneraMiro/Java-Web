package com.ingenera.linkeduot.repositories;

import com.ingenera.linkeduot.models.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {



}
