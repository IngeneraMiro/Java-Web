package com.ingenera.lab1.models.servicemodels;

import com.ingenera.lab1.models.entities.Role;

import java.time.LocalDateTime;
import java.util.Set;

public class UserServiceModel extends BaseServiceModel{

    private String username;
    private String firstName;
    private String lastName;
    private Boolean active;
    private Set<RoleServiceModel> role;
    private String imageUrl;
    private LocalDateTime created;
    private LocalDateTime modified;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<RoleServiceModel> getRole() {
        return role;
    }

    public void setRole(Set<RoleServiceModel> role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
