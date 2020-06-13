package com.ingenera.lab2.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    private String name;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Brand brand;

    public Model() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "start_year")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Column(name = "end_year")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @Column(name = "created")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
