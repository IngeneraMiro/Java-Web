package com.ingenera.examprep.models.bindmodels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ItemBindModel {

    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String gender;

    public ItemBindModel() {
    }

    @NotNull(message = "Name can not be empty!")
    @Length(min = 2,message = "Name must be at least two characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 3,message = "Description must be at least three characters!")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0",inclusive = true,message = "Price must be more or equal to 0!")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
