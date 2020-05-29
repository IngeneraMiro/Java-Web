package com.ingenera.workshop.repositories;

import com.ingenera.workshop.models.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,String> {


}
