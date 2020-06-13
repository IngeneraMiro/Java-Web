package com.ingenera.springworkshop.models.bindmodels;

import org.hibernate.validator.constraints.Length;

public class UserLogBindModel extends BaseBindModel{

    private String username;
    private String password;

    public UserLogBindModel() {
    }

    @Length(min = 2,max = 10,message = "Username must be between 2 and 10 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 2,max = 10,message = "Password must be between 2 and 10 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
