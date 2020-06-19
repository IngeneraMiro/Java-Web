package com.ingenera.linkeduot.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

   private BigDecimal budget;
   private String description;
   private String name;
   private String town;
   private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    @Column(name = "budget")
    @DecimalMin(value = "0",inclusive = true)
    @NotNull
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @Column(name = "description",columnDefinition = "TEXT")
    @NotNull
    @Length(min = 10)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "name",unique = true,nullable = false)
    @Length(min = 2,max = 10)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "town",nullable = false)
    @Length(min = 2,max = 10)
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
