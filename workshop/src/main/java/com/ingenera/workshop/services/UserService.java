package com.ingenera.workshop.services;

import com.ingenera.workshop.models.servicemodels.UserServiceModel;

public interface UserService {

    UserServiceModel getUserByName(String name);

    UserServiceModel registerUser(UserServiceModel userServiceModel);

}
