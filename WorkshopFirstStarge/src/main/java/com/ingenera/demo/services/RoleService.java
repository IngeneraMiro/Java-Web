package com.ingenera.demo.services;

import com.ingenera.demo.models.entities.Role;
import com.ingenera.demo.models.servicemodels.RoleServiceModel;

public interface RoleService {

    RoleServiceModel getByName(String name);
    Role  getRole(String name);
}
