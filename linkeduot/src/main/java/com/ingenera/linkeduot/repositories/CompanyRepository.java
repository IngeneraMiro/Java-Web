package com.ingenera.linkeduot.repositories;

import com.ingenera.linkeduot.models.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {

   Company getByName(String name);

}
