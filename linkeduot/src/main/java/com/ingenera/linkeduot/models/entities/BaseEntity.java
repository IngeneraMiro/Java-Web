package com.ingenera.linkeduot.models.entities;

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
    @Column(name = "id")
    @GeneratedValue(generator ="UUID-String")
    @GenericGenerator(name = "UUID-String",strategy = "org.hibernate.id.UUIDGenerator")

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
