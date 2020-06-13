package com.ingenera.springworkshop.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String git;
    private Role role;

    public User() {
    }

    @Column(name = "user_name", unique = true, nullable = false)
    @Length(min = 2,max = 10,message = "Username must be between 2 and 10 characters")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password", nullable = false)
    @Length(min = 2,max = 10,message = "Password must be between 2 and 10 characters")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = " Enter valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "github_address", nullable = false, unique = true)
    @Pattern(regexp = "^https:\\/\\/github.com\\/.*")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }


    @ManyToOne(cascade = CascadeType.REFRESH)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
