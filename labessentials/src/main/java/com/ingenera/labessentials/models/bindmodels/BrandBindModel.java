package com.ingenera.labessentials.models.bindmodels;

import java.time.LocalDateTime;

public class BrandBindModel extends BaseBindModel {

    private String name;
    private LocalDateTime created;
    private LocalDateTime modified;

    public BrandBindModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
