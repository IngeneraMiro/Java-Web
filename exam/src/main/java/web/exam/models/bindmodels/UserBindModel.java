package web.exam.models.bindmodels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserBindModel {

    private String username;
    private String password;
    private String confirmPassword;
    private String email;

    public UserBindModel() {
    }

    @NotNull(message = "Username can not be null!")
    @Length(min = 3,max = 21,message = "Username must be between 3 and 20 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password can not be null!")
    @Length(min = 3,max = 21,message = "Password must be between 3 and 20 characters!")
    public String getPassword() {
        return password;
    }

    @NotNull
    @Length(min = 3,max = 21,message = "Password must be between 3 and 20 characters!")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Enter valid email address!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
