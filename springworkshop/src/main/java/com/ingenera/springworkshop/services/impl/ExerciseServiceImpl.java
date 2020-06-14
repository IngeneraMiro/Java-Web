package com.ingenera.springworkshop.services.impl;

import com.ingenera.springworkshop.models.bindmodels.ExerciseBindModel;
import com.ingenera.springworkshop.models.entities.Exercise;
import com.ingenera.springworkshop.repositories.ExerciseRepository;
import com.ingenera.springworkshop.services.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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
        exerciseBindModel.setDueDate(exerciseBindModel.getDueDate().with(LocalTime.of(23,59,59)));
        Exercise exercise = this.mapper.map(exerciseBindModel,Exercise.class);
        return this.exerciseRepository.saveAndFlush(exercise);
    }

    @Override
    public Exercise getExerciseByName(String name) {
        return this.exerciseRepository.getByName(name);
    }

    @Override
    public List<Exercise> getActiveExercises(LocalDateTime date) {
        return this.exerciseRepository.getActive(date);
    }

    @Override
    public List<String> getAllExercise() {
        return this.exerciseRepository.findAll().stream().map(Exercise::getName).collect(Collectors.toList());
    }
}
