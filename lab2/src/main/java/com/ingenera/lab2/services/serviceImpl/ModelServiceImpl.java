package com.ingenera.lab2.services.serviceImpl;

import com.ingenera.lab2.repositories.ModelRepository;
import com.ingenera.lab2.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper mapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper mapper) {
        this.modelRepository = modelRepository;
        this.mapper = mapper;
    }
}
