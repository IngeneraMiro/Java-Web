package com.ingenera.examprep.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private ItemCategories category;
    private String description;

    public Category() {
    }

    public Category(ItemCategories category){
        this.category = category;
    }

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    public ItemCategories getCategory() {
        return category;
    }

    public void setCategory(ItemCategories category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
