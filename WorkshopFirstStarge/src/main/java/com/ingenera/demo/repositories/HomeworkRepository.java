package com.ingenera.demo.repositories;

import com.ingenera.demo.models.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, String> {


}
