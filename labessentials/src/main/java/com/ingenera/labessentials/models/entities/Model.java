package com.ingenera.labessentials.models.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "models")
public class Model extends BaseEntity {

    private String name;
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;
    private Date created;
    private Date modified;
    private Brand brand;
    private VehicleType type;

    public Model() {
    }

    public Model(String name,VehicleType type,Integer startYear,Integer endYear,String imageUrl){
          this.name = name;
          this.type = type;
          this.startYear = startYear;
          this.endYear = endYear;
          this.imageUrl = imageUrl;
          this.created = new Date();
          this.modified = new Date();
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "image_url")
    @Length(min = 6,max = 512)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "start_year")
    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    @Column(name = "end_year")
    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    @Column(name = "created")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "modified")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }
}
