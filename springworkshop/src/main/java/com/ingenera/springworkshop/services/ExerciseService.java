package com.ingenera.springworkshop.services;

import com.ingenera.springworkshop.models.bindmodels.ExerciseBindModel;
import com.ingenera.springworkshop.models.entities.Exercise;

public interface ExerciseService {

    Exercise createNewExercise(ExerciseBindModel exerciseBindModel);

}
