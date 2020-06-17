package com.ingenera.springworkshop.models.viewmodels;

import com.ingenera.springworkshop.models.entities.Exercise;
import com.ingenera.springworkshop.models.entities.User;

public class HomeworkViewModel extends BaseServiceModel{
    private String gitAddress;
    private User author;
    private Exercise exercise;

    public HomeworkViewModel() {
    }

    public String getGitAddress() {
        return gitAddress;
    }

    public void setGitAddress(String gitAddress) {
        this.gitAddress = gitAddress;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
