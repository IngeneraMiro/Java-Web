package com.ingenera.examprep.services.servicempl;

import com.ingenera.examprep.models.bindmodels.UserBindModel;
import com.ingenera.examprep.models.entities.User;
import com.ingenera.examprep.repositories.UserRepository;
import com.ingenera.examprep.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public String addUser(UserBindModel userBindModel) {
        User user = this.mapper.map(userBindModel,User.class);
        this.userRepository.saveAndFlush(user);
        return user.getId();
    }

    @Override
    public String getUserByUsernameAndPass(String username, String password) {
       User user = this.userRepository.findByUsernameAndPassword(username,password);
       return user.getId();
    }


}
