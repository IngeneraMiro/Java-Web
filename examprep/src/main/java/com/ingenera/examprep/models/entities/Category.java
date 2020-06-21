package com.ingenera.examprep.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    private ItemCategories category;

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
}
