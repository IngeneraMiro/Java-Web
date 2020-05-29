package com.ingenera.workshop.services.impl;

import com.ingenera.workshop.models.entities.Role;
import com.ingenera.workshop.models.entities.Roles;
import com.ingenera.workshop.models.servicemodels.RoleServiceModel;
import com.ingenera.workshop.repositories.RoleRepository;
import com.ingenera.workshop.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @PostConstruct
    public void init(){
        if(this.roleRepository.count()==0){
            Role admin = new Role(Roles.ADMIN);
            Role user = new Role(Roles.USER);
            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public RoleServiceModel getNyName(String name) {
        return this.roleRepository.getByName(name).map(role -> this.mapper.map(role,RoleServiceModel.class)).orElse(null);
    }
}
