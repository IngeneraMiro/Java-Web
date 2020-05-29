package com.ingenera.demo.services.impl;

import com.ingenera.demo.models.entities.User;
import com.ingenera.demo.models.servicemodels.UserServiceModel;
import com.ingenera.demo.repositories.UserRepository;
import com.ingenera.demo.services.RoleService;
import com.ingenera.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @Override
    public UserServiceModel getUserByName(String name) {
        return this.userRepository.getByUsername(name).map(user->this.mapper.map(user,UserServiceModel.class)).orElse(null);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {

        userServiceModel.setRole(this.roleService.getNyName(this.userRepository.count()==0? "ADMIN":"USER"));
        this.userRepository.saveAndFlush(this.mapper.map(userServiceModel,User.class));
        return userServiceModel;
    }
}
