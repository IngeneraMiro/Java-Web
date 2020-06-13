package com.ingenera.springworkshop.services.impl;

import com.ingenera.springworkshop.models.bindmodels.UserBindModel;
import com.ingenera.springworkshop.models.entities.User;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.models.viewmodels.UserViewModel;
import com.ingenera.springworkshop.repositories.UserRepository;
import com.ingenera.springworkshop.services.RoleService;
import com.ingenera.springworkshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
     private final UserRepository userRepo;
     private final RoleService roleService;
     private final ModelMapper mapper;

     @Autowired
    public UserServiceImpl(UserRepository userRepo, RoleService roleService, ModelMapper mapper) {
        this.userRepo = userRepo;
         this.roleService = roleService;
         this.mapper = mapper;
    }

    @PostConstruct
    private void firstUser(){
         if(this.userRepo.count()==0) {
             User user = new User();
             user.setUsername("miro");
             user.setPassword("password");
             user.setEmail("miro66@mail.bg");
             user.setGit("https://github.com/IngeneraMiro/Java-Web");
             user.setRole(this.roleService.getRoleByName("ADMIN"));
             this.userRepo.saveAndFlush(user);
         }
    }

    @Override
    public UserServiceModel getServiceModel(String username) {
        return this.userRepo.findByUsername(username).map(u->this.mapper.map(u,UserServiceModel.class)).orElse(null);
    }

    @Override
    public User getUserByName(String username) {
        return this.userRepo.findByUsername(username).orElse(null);
    }

    @Override
    public boolean checkUsername(String username) {
        return this.userRepo.existsByUsername(username);
    }

    @Override
    public UserServiceModel registerUser(UserBindModel userBindModel) {
         User user = mapper.map(userBindModel,User.class);
         if(userRepo.count()>0){
             user.setRole(roleService.getRoleByName("USER"));
         }else{
             user.setRole(roleService.getRoleByName("ADMIN"));
         }
        return mapper.map(userRepo.save(user),UserServiceModel.class);
    }

    @Override
    public List<UserBindModel> getAllUsers() {
     return   this.userRepo.findAll().stream().map(u->this.mapper.map(u,UserBindModel.class)).collect(Collectors.toList());

    }

    @Override
    public void changeRole(String username, String role) {
        User user = this.userRepo.findByUsername(username).orElse(null);
        assert user != null;
        user.setRole(this.roleService.getRoleByName(role.toUpperCase()));
        this.userRepo.saveAndFlush(user);
    }

    @Override
    public UserViewModel getUserDetails(String name) {
         User user = this.userRepo.findByUsername(name).orElse(null);
         UserViewModel userViewModel = this.mapper.map(user,UserViewModel.class);
         return userViewModel;
    }
}
