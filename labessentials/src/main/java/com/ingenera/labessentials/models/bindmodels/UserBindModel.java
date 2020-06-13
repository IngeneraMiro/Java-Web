package com.ingenera.labessentials.models.bindmodels;

import com.ingenera.labessentials.models.entities.Role;
import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

public class UserBindModel extends BaseBindModel {

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Set<Role> role = new HashSet<>();

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

    @Length(min = 4,max = 80,message = "Password must be between 4 and 80 symbols!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}
