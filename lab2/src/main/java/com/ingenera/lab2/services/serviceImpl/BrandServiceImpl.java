package com.ingenera.lab2.services.serviceImpl;

import com.ingenera.lab2.models.bindmodels.BrandBindModel;
import com.ingenera.lab2.models.entities.Brand;
import com.ingenera.lab2.repositories.BrandRepository;
import com.ingenera.lab2.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper mapper) {
        this.brandRepository = brandRepository;
        this.mapper = mapper;
    }

    @Override
    public String addBrand(BrandBindModel model) {
        if (this.brandRepository.getByName(model.getName()) == null) {
            model.setCreated(LocalDateTime.now());
            model.setModified(LocalDateTime.now());
            this.brandRepository.saveAndFlush(this.mapper.map(model, Brand.class));
            return String.format("Successfully added Brand %s", model.getName());
        }

        return String.format("Brand %s already exist", model.getName());
    }
}
