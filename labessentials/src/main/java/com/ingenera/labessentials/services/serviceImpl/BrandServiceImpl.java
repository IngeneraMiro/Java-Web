package com.ingenera.labessentials.services.serviceImpl;


import com.ingenera.labessentials.models.bindmodels.BrandBindModel;
import com.ingenera.labessentials.models.entities.Brand;
import com.ingenera.labessentials.repositories.BrandRepository;
import com.ingenera.labessentials.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

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
    public Long getBrandsCount() {
        return this.brandRepository.count();
    }

    @Override
    public void createBrand(Brand brand) {
        this.brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return this.brandRepository.getAllByIdIsNotNull();
    }
}
