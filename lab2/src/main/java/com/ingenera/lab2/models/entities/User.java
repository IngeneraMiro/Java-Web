package com.ingenera.lab2.models.entities;

import net.bytebuddy.build.ToStringPlugin;
import org.aspectj.lang.annotation.After;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Boolean active;
    private Set<UserRole> role;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;
    @ToStringPlugin.Exclude
    private Set<Offer> offers;

    public User() {
    }

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 3, max = 60)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name", nullable = false)
    @Length(min = 3, max = 60)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 3, max = 60)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "password", nullable = false, updatable = false)
    @Length(min = 4, max = 60)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> role) {
        this.role = role;
    }

    @Column(name = "image_url", nullable = false)
    @Length(min = 8, max = 512)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "created", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @After("1988")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @After("1988")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @OneToMany(mappedBy = "seller")
    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }
}
