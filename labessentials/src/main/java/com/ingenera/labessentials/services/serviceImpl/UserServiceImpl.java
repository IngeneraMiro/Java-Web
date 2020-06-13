package com.ingenera.labessentials.services.serviceImpl;


import com.ingenera.labessentials.models.bindmodels.UserBindModel;
import com.ingenera.labessentials.models.bindmodels.UserLoginBindModel;
import com.ingenera.labessentials.models.entities.Role;
import com.ingenera.labessentials.models.entities.User;
import com.ingenera.labessentials.repositories.UserRepository;
import com.ingenera.labessentials.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public Long getUsersCount() {
        return this.userRepository.count();
    }

    @Override
    public void createUser(User user) {
        user.setActive(true);
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        this.userRepository.save(user);
    }

    @Override
    public User saveUser(UserBindModel userBindModel) {
        User user = this.mapper.map(userBindModel,User.class);
        if(userBindModel.getRole().equals(Role.ADMIN)){
            throw new IllegalArgumentException("You can not create Admin!");
        }
        if(this.userRepository.getByUsername(user.getUsername())!=null){
            throw new IllegalArgumentException(String.format("User with username %s already exist!"));
        }

        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setActive(true);

//        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public User checkUser(UserLoginBindModel userLoginBindModel) {
        return this.userRepository.findByUsernameAndPassword(userLoginBindModel.getUsername()
                ,userLoginBindModel.getPassword()).orElse(null);

    }
}
