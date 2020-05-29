package com.ingenera.demo.services;

import com.ingenera.demo.models.servicemodels.UserServiceModel;

public interface UserService {

    UserServiceModel getUserByName(String name);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

}
