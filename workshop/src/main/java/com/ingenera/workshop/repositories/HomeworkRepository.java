package com.ingenera.workshop.repositories;

import com.ingenera.workshop.models.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,String> {


}
