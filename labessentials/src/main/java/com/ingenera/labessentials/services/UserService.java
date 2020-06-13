package com.ingenera.labessentials.services;

import com.ingenera.labessentials.models.bindmodels.UserBindModel;
import com.ingenera.labessentials.models.bindmodels.UserLoginBindModel;
import com.ingenera.labessentials.models.entities.User;

public interface UserService {

    Long getUsersCount();
    void createUser(User user);
    User checkUser(UserLoginBindModel userLoginBindModel);
    User saveUser(UserBindModel userBindModel);
}
