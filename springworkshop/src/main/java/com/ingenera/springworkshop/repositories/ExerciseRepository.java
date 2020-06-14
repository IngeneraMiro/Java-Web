package com.ingenera.springworkshop.repositories;

import com.ingenera.springworkshop.models.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

   Exercise getByName(String name);

   @Query(value = "select e from Exercise as e where e.dueDate > ?1")
    List<Exercise> getActive(LocalDateTime date);

}
