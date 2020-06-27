package web.exam.services;

import web.exam.models.bindmodels.UserBindModel;
import web.exam.models.entities.User;

public interface UserService {

    User addUser(UserBindModel model);

    User logUser(String username, String password);


}
