package com.ingenera.demo.services.impl;

import com.ingenera.demo.models.entities.Role;
import com.ingenera.demo.models.entities.User;
import com.ingenera.demo.models.servicemodels.RoleServiceModel;
import com.ingenera.demo.models.servicemodels.UserServiceModel;
import com.ingenera.demo.repositories.UserRepository;
import com.ingenera.demo.services.RoleService;
import com.ingenera.demo.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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
        User user = this.userRepository.getByUsername(name);
        if(user==null){
            return null;
        }
        return this.mapper.map(user, UserServiceModel.class);
    }

    @Override
    public UserServiceModel registerUser(UserServiceModel userServiceModel) {
        User user = this.mapper.map(userServiceModel, User.class);
        user.setRole(this.roleService.getByName(this.userRepository.count() == 0 ? "ADMIN" : "USER"));


        this.userRepository.saveAndFlush(user);
        return userServiceModel;
    }
}
