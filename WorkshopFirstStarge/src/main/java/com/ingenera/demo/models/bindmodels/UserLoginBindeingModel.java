package com.ingenera.demo.models.bindmodels;

import org.hibernate.validator.constraints.Length;

public class UserLoginBindeingModel {

    private String username;
    private String password;

    public UserLoginBindeingModel() {
    }

    @Length(min = 2, max = 10, message = "Username must be between 2 and 10 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 10, message = "Password must be betweeen 3 and 10 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}