package com.ingenera.lab1.models.servicemodels;

import com.ingenera.lab1.models.entities.Roles;

public class RoleServiceModel extends BaseServiceModel {

    private Roles role;

    public RoleServiceModel(Roles role) {
        this.role = role;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
