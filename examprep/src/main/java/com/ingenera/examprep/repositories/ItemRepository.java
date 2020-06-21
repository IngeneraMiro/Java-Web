package com.ingenera.examprep.repositories;

import com.ingenera.examprep.models.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {

     Item getById(String id);

}
