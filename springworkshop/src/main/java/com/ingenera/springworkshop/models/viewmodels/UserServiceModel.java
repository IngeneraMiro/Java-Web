package com.ingenera.springworkshop.models.viewmodels;

import com.ingenera.springworkshop.models.entities.Role;

public class UserServiceModel extends BaseServiceModel{

    private String username;
    private String password;
    private Role role;

    public UserServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
