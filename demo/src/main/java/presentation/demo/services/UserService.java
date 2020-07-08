package presentation.demo.services;

import presentation.demo.models.bindmodels.UserBindModel;
import presentation.demo.models.bindmodels.UserLogBindModel;
import presentation.demo.models.entities.User;
import presentation.demo.models.viewmodels.UserLogViewModel;

public interface UserService {

    User addUser(UserBindModel model);
    UserLogViewModel logUser(UserLogBindModel model);
    Boolean begin();

}
