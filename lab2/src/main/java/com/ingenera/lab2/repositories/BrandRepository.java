package com.ingenera.lab2.repositories;

import com.ingenera.lab2.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Brand getByName(String name);

}
