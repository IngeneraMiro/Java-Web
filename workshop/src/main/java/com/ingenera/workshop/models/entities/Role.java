package com.ingenera.workshop.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

    private Roles name;

    public Role() {
    }

    @Column(name = "name",unique = true,nullable = false)
    @Enumerated(EnumType.STRING)
    public Roles getName() {
        return name;
    }

    public void setName(Roles name) {
        this.name = name;
    }
}
