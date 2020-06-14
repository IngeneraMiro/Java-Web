package com.ingenera.springworkshop.services;

import com.ingenera.springworkshop.models.bindmodels.ExerciseBindModel;
import com.ingenera.springworkshop.models.entities.Exercise;

import java.time.LocalDateTime;
import java.util.List;

public interface ExerciseService {

    Exercise createNewExercise(ExerciseBindModel exerciseBindModel);

    Exercise getExerciseByName(String name);

    List<Exercise> getActiveExercises(LocalDateTime date);

    List<String> getAllExercise();
}
