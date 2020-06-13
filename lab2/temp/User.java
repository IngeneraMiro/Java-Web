package com.ingenera.lab2.models.entities;

import org.aspectj.lang.annotation.After;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Set<UserRole> role;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;

    public User() {
    }

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 3)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    public Set<UserRole> getRole() {
        return role;
    }

    public void setRole(Set<UserRole> role) {
        this.role = role;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "created", nullable = false)
    @After("1988")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified", nullable = false)
    @After("1988")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
