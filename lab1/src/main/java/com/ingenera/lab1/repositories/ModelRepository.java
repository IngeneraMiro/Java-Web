package com.ingenera.lab1.repositories;

import com.ingenera.lab1.models.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {



}
