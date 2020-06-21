package com.ingenera.examprep.services;

import com.ingenera.examprep.models.bindmodels.UserBindModel;

public interface UserService {

    String addUser(UserBindModel userBindModel);

    String getUserByUsernameAndPass(String username,String password);
}
