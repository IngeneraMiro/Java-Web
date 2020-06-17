package com.ingenera.springworkshop.services;

import com.ingenera.springworkshop.models.bindmodels.UserBindModel;
import com.ingenera.springworkshop.models.entities.User;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.models.viewmodels.UserViewModel;

import java.util.List;

public interface UserService {

    UserServiceModel getServiceModel(String username);

    User getUserByName(String username);

    User getUserById(Long id);

    boolean checkUsername(String username);

    UserServiceModel registerUser(UserBindModel userBindModel);

    List<UserBindModel> getAllUsers();

    void changeRole(String username, String role);

    UserViewModel getUserDetails(String name);
    UserViewModel getUserDetailsById(Long id);
}
