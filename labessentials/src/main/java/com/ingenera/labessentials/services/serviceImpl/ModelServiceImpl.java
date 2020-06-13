package com.ingenera.labessentials.services.serviceImpl;


import com.ingenera.labessentials.repositories.ModelRepository;
import com.ingenera.labessentials.services.ModelService;
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
