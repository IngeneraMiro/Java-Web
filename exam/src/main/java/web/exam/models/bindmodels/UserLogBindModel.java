package web.exam.models.bindmodels;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class UserLogBindModel {

    private String username;
    private String password;

    public UserLogBindModel() {
    }

    @NotNull(message = "Username can not be null!")
    @Length(min = 3,max = 20,message = "Product name must be between 3 and 20 characters!")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @NotNull(message = "Password can not be null!")
    @Length(min = 3,max = 20,message = "Password name must be between 3 and 20 characters!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
