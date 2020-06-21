package com.ingenera.examprep.models.bindmodels;

import org.hibernate.validator.constraints.Length;

public class UserLogModel {

    private String username;
    private String password;

    public UserLogModel() {
    }

    @Length(min = 2,message = "Username must be at least two characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2,message = "Password must be at least two characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
