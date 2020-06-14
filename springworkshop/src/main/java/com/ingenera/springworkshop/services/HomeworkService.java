package com.ingenera.springworkshop.services;

import com.ingenera.springworkshop.models.bindmodels.HomeworkBindModel;
import com.ingenera.springworkshop.models.entities.Homework;
import com.ingenera.springworkshop.models.viewmodels.UserServiceModel;

import java.util.List;

public interface HomeworkService {

    Homework addHomework(HomeworkBindModel homeworkBindModel, UserServiceModel userServiceModel);
    List<String> getHomeworksByUser(Long id);
}
