package com.ingenera.oldexam.repositories;

import com.ingenera.oldexam.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User getByUsernameAndPassword(String id,String password);

}
