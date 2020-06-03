package com.ingenera.demo.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private String name;

    public Role() {
    }

    public Role(String name){
        this.name = name;
    }

    @Column(name = "name",unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
