package com.ingenera.labessentials.services;


import com.ingenera.labessentials.models.bindmodels.BrandBindModel;
import com.ingenera.labessentials.models.entities.Brand;

import java.util.List;

public interface BrandService {

    Long getBrandsCount();
    void createBrand(Brand brand);
    List<Brand> getAllBrands();
}
