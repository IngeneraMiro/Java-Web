package com.ingenera.springworkshop.repositories;

import com.ingenera.springworkshop.models.entities.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Long> {

       @Query(value = "select h from Homework as h where h.author.id = ?1")
       List<Homework> getAllByUser(Long id);

}
