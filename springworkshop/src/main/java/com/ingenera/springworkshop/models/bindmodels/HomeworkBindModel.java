package com.ingenera.springworkshop.models.bindmodels;


import com.ingenera.springworkshop.models.entities.Exercise;

import javax.validation.constraints.Pattern;

public class HomeworkBindModel {

    private String exercise;
    private String git;

    public HomeworkBindModel() {
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    @Pattern(regexp = "^https:\\/\\/github.com\\/.*",message = "Invalid github address")
    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }
}
