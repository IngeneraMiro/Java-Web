package com.ingenera.lab2.models.bindmodels;

import org.hibernate.validator.constraints.Length;

public class UserBindModel extends BaseBindModel {

    private String username;
    private String firstName;
    private String lastName;
    private String imageUrl;

    public UserBindModel() {
    }

    @Length(min = 3, max = 60, message = "Username must be between 3 and 60 symbols!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Length(min = 3, max = 60, message = "First name must be between 3 and 60 symbols!")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3, max = 60, message = "Last name must be between 3 and 60 symbols!")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Length(min = 8, max = 512, message = "URL must be between 8 and 512 symbols!")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
