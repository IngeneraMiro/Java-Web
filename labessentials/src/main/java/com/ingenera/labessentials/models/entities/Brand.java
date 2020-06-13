package com.ingenera.labessentials.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    private String name;
    private Date created = new Date();
    private Date modified = new Date();
    private List<Model> models = new ArrayList<>();

    public Brand() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "created", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Column(name = "modified", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }

    public static Brand create(String name, Set<Model> models) {
        Brand brand = new Brand();
        brand.setName(name);
        models.stream().sorted(Comparator.comparing(Model::getName)).forEach(m->{m.setBrand(brand);
                   brand.getModels().add(m);});
        return brand;
    }
}
