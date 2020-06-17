package com.ingenera.springworkshop.services.impl;

import com.ingenera.springworkshop.models.bindmodels.HomeworkBindModel;
import com.ingenera.springworkshop.models.entities.Exercise;
import com.ingenera.springworkshop.models.entities.Homework;
import com.ingenera.springworkshop.models.viewmodels.HomeworkViewModel;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;
import com.ingenera.springworkshop.repositories.HomeworkRepository;
import com.ingenera.springworkshop.services.ExerciseService;
import com.ingenera.springworkshop.services.HomeworkService;
import com.ingenera.springworkshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    private final HomeworkRepository homeworkRepository;
    private final UserService userService;
    private final ExerciseService exerciseService;
    private final ModelMapper mapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository homeworkRepository, UserService userService, ExerciseService exerciseService, ModelMapper mapper) {
        this.homeworkRepository = homeworkRepository;
        this.userService = userService;
        this.exerciseService = exerciseService;
        this.mapper = mapper;
    }

    @Override
    public Homework addHomework(HomeworkBindModel homeworkBindModel, UserServiceModel userServiceModel) {
        Homework homework = new Homework();
        homework.setGitAddress(homeworkBindModel.getGit());
        homework.setExercise(this.exerciseService.getExerciseByName(homeworkBindModel.getExercise()));
        homework.setAddedOn(LocalDateTime.now());
        homework.setAuthor(this.userService.getUserByName(userServiceModel.getUsername()));
        return this.homeworkRepository.saveAndFlush(homework);
    }

    @Override
    public Homework getHomeworkById(Long id) {
        return this.homeworkRepository.getById(id);
    }

    @Override
    public List<String> getHomeworksByUser(Long id) {
        return homeworkRepository.getAllByUser(id).stream().map(h->h.getExercise().getName()).collect(Collectors.toList());
    }

    @Override
    public HomeworkViewModel getRandomHomework(Long id) {
        Homework homework = new Homework();
        while (homework.getId()==null || homework.getAuthor().getId()==id){
            Long gLong = 1 + (long) (Math.random() * (this.homeworkRepository.count() - 1));
            homework = this.homeworkRepository.getById(gLong);
        }
       return this.mapper.map(homework,HomeworkViewModel.class);
    }
}
