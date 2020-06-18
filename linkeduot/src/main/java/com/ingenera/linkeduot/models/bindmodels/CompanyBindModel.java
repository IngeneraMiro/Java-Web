package com.ingenera.linkeduot.models.bindmodels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompanyBindModel {

    private BigDecimal budget;
    private String description;
    private String name;
    private String town;

    public CompanyBindModel() {
    }

    @NotNull(message = "Budget can not be empty")
    @DecimalMin(value = "0",inclusive = true,message = "Budget must be positive!")
    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    @NotNull(message = "Description can not be empty")
    @Length(min = 10,message = "Description mush be at least 10 symbols long! ")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = "Name can not be empty")
    @Length(min = 2,max = 10,message = "Name must be between 2 and 10 symbols!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "Town can not be empty")
    @Length(min = 2,max = 10,message = "Town must be between 2 and 10 symbols!")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
