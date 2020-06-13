package com.ingenera.lab2.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private String description;
    private Engins engine;
    private String imageUrl;
    private int mileage;
    private double price;
    private Transmission transmission;
    private int year;
    private LocalDateTime created;
    private LocalDateTime modified;
    private Model model;
    private User seller;

    public Offer() {
    }

    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "engine", nullable = false)
    @Enumerated(EnumType.STRING)
    public Engins getEngine() {
        return engine;
    }

    public void setEngine(Engins engine) {
        this.engine = engine;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "mileage", nullable = false)
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "transmission", nullable = false)
    @Enumerated(EnumType.STRING)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year", nullable = false)
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column(name = "created", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
