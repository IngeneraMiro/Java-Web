package com.ingenera.oldexam.services.serviceimpl;

import com.ingenera.oldexam.models.bindingmodels.UserBindModel;
import com.ingenera.oldexam.models.entities.User;
import com.ingenera.oldexam.repositories.UserRepository;
import com.ingenera.oldexam.services.UserService;
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
    public User getLoggedUser(String username, String password) {
        return this.userRepository.getByUsernameAndPassword(username,password);
    }

    @Override
    public void addUser(UserBindModel user) {
        this.userRepository.saveAndFlush(this.mapper.map(user,User.class));
    }
}
