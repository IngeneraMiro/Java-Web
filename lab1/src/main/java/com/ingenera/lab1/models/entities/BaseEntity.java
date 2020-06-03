package com.ingenera.lab1.models.entities;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {
    private long id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false,updatable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
