package com.ingenera.workshop.services.impl;

import com.ingenera.workshop.models.servicemodels.UserServiceModel;
import com.ingenera.workshop.repositories.UserRepository;
import com.ingenera.workshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel getUserByName(String name) {
        return this.userRepository.getByUsername(name).map(user->this.mapper.map(user,UserServiceModel.class)).orElse(null);
    }
}
