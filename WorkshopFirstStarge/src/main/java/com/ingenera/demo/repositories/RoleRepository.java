package com.ingenera.demo.repositories;

import com.ingenera.demo.models.entities.Role;
import com.ingenera.demo.models.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByName(String name);

    Role getByName(String name);

}
