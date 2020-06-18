package com.ingenera.linkeduot.models.bindmodels;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

public class EmployeeBindModel {

    private Date birthDate;
    private String educationLevel;
    private String firstName;
    private String jobTitle;
    private String lastName;
    private BigDecimal salary;
    private String company;

    public EmployeeBindModel() {
    }

    @NotNull(message = "BirthDate can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @NotNull(message = "Education can not be empty")
    public String getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(String educationLevel) {
        this.educationLevel = educationLevel;
    }

    @NotNull(message = ("First name can not be empty"))
    @Length(min = 2,message = "First name must be at least")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull(message = "Job title can not be empty")
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @NotNull(message = ("First name can not be empty"))
    @Length(min = 2,message = "Last name must be at least")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull(message = "Salary can not be empty")
    @DecimalMin(value = "0",inclusive = true,message = "Salary must be positive")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @NotNull(message = "Company can not be empty")
    @Length(min = 2,max = 10,message = "Company must be between 2 and 10 symbols" )
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
