package com.ingenera.springworkshop.services.impl;

import com.ingenera.springworkshop.models.bindmodels.ExerciseBindModel;
import com.ingenera.springworkshop.models.entities.Exercise;
import com.ingenera.springworkshop.repositories.ExerciseRepository;
import com.ingenera.springworkshop.services.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ModelMapper mapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository exerciseRepository, ModelMapper mapper) {
        this.exerciseRepository = exerciseRepository;
        this.mapper = mapper;
    }

    @Override
    public Exercise createNewExercise(ExerciseBindModel exerciseBindModel) {
        return this.exerciseRepository.saveAndFlush(this.mapper.map(exerciseBindModel,Exercise.class));
    }
}
