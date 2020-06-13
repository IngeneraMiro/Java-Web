package com.ingenera.demo.models.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    private UUID id;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(generator = "UUID-String")
    @GenericGenerator(name = "UUID-String", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false, unique = true)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
