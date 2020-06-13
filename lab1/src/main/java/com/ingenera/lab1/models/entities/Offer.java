package com.ingenera.lab1.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private String description;
    private Engins engine;
    private String imageUrl;
    private Integer mileage;
    private Double price;
    private Transmission transmission;
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Model model;
    private User seller;

    public Offer() {
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "engine")
    @Enumerated(EnumType.ORDINAL)
    public Engins getEngine() {
        return engine;
    }

    public void setEngine(Engins engine) {
        this.engine = engine;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "milesge")
    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "transmission")
    @Enumerated(EnumType.ORDINAL)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    @ManyToOne
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
