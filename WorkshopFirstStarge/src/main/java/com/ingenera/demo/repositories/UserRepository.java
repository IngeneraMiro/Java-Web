package com.ingenera.demo.repositories;

import com.ingenera.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value="select u from User as u where u.username = 'miro' ")
    User getAdmin();

    User getByUsername(String username);
}
