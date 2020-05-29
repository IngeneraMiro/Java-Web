package com.ingenera.workshop.models.servicemodels;

import com.ingenera.workshop.models.entities.Roles;

public class RoleServiceModel extends BaseServiceModel{

    private Roles roles;

    public RoleServiceModel() {
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }
}
