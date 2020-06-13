package com.ingenera.demo.services.impl;

import com.ingenera.demo.models.entities.Role;
import com.ingenera.demo.models.entities.Roles;
import com.ingenera.demo.models.servicemodels.RoleServiceModel;
import com.ingenera.demo.repositories.RoleRepository;
import com.ingenera.demo.services.RoleService;
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
    public void init() {
        if (this.roleRepository.count() == 0) {
            Role admin = new Role("ADMIN");
            Role user = new Role("USER");
            this.roleRepository.save(admin);
            this.roleRepository.save(user);
        }
    }

    @Override
    public Role getByName(String name) {

        return this.roleRepository.findByName(name).orElse(null);
    }

    @Override
    public Role getRole(String name) {
        return this.roleRepository.getByName(name);
    }
}
