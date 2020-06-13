package com.ingenera.lab1.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Brand() {
    }

    @Column(name = "name", unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
