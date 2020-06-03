package com.ingenera.lab1.services.serviceimpl;

import com.ingenera.lab1.models.entities.Role;
import com.ingenera.lab1.repositories.RoleRepository;
import com.ingenera.lab1.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Set<Role> getAllRoles() {
        return this.roleRepository.getAllRoles();
    }
}
