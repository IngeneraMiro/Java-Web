package com.ingenera.workshop.repositories;

import com.ingenera.workshop.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,String> {

   Optional<User> getByUsername(String username);
}
