package com.ingenera.labessentials.models.bindmodels;

public abstract class BaseBindModel {

    private Long id;

    public BaseBindModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
