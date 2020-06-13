package com.ingenera.lab1.repositories;

import com.ingenera.lab1.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r WHERE r.id is not null")
    Set<Role> getAllRoles();

}
