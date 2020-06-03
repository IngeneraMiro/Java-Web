package com.ingenera.lab1.repositories;

import com.ingenera.lab1.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {



}
