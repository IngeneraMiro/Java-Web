package com.ingenera.labessentials.repositories;


import com.ingenera.labessentials.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

     User getByUsername(String username);
     Optional<User> findByUsernameAndPassword(String username,String password);
}
