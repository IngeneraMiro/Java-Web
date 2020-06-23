package com.ingenera.oldexam.models.bindingmodels;

import org.hibernate.validator.constraints.Length;

public class UserBindModel {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String country;

    public UserBindModel() {
    }

    @Length(min = 1,message = "Username can not be empty!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 1,message = "Password can not be empty!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Length(min = 1,message = "Password conformation can not be empty!")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Length(min = 1,message = "Email can not be empty!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 1,message = "Country can not be empty!")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
