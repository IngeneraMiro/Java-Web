package com.ingenera.labessentials.repositories;


import com.ingenera.labessentials.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> getAllByIdIsNotNull();

}
