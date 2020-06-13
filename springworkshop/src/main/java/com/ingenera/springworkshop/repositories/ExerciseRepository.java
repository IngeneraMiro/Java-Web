package com.ingenera.springworkshop.repositories;

import com.ingenera.springworkshop.models.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {



}
