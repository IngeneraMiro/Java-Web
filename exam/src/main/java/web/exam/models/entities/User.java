package web.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    private String username;
    private String password;
    private String email;

    public User() {
    }

    @Column(name = "users",nullable = false,unique = true)
    @Length(min = 3,max = 10)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "password",nullable = false)
    @Length(min = 2,max = 10)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
