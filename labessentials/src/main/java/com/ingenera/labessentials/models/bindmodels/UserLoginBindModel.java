package com.ingenera.labessentials.models.bindmodels;

public class UserLoginBindModel extends BaseBindModel{

    private String username;
    private String password;

    public UserLoginBindModel() {
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
}
