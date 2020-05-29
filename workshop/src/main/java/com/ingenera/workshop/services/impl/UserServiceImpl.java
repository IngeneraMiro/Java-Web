package com.ingenera.workshop.services.impl;

import com.ingenera.workshop.models.entities.Roles;
import com.ingenera.workshop.models.entities.User;
import com.ingenera.workshop.models.servicemodels.RoleServiceModel;
import com.ingenera.workshop.models.servicemodels.UserServiceModel;
import com.ingenera.workshop.repositories.UserRepository;
import com.ingenera.workshop.services.RoleService;
import com.ingenera.workshop.services.UserService;
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
