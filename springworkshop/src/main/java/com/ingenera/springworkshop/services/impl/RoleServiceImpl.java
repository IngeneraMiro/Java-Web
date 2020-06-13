package com.ingenera.springworkshop.services.impl;

import com.ingenera.springworkshop.models.entities.Role;
import com.ingenera.springworkshop.repositories.RoleRepository;
import com.ingenera.springworkshop.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepo;
    private final ModelMapper mapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepo, ModelMapper mapper) {
        this.roleRepo = roleRepo;
        this.mapper = mapper;
    }

    @PostConstruct
    private void init(){
        if(this.roleRepo.count()==0){
            this.roleRepo.save(new Role("ADMIN"));
            this.roleRepo.save(new Role("USER"));
        }
    }


    @Override
    public Role getRoleByName(String name) {
        return this.roleRepo.getByName(name);
    }
}
