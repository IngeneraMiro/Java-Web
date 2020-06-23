package com.ingenera.oldexam.services;

import com.ingenera.oldexam.models.bindingmodels.UserBindModel;
import com.ingenera.oldexam.models.entities.User;

public interface UserService {

    User getLoggedUser(String username,String password);
    void addUser(UserBindModel user);

}
