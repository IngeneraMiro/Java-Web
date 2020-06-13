package com.ingenera.lab2.models.entities;

public class Role extends BaseEntity {

    private UserRole role;

    public Role() {
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
