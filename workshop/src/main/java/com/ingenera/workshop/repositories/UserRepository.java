package com.ingenera.workshop.repositories;

import com.ingenera.workshop.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User ,String> {


}
