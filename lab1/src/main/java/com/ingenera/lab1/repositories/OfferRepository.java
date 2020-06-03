package com.ingenera.lab1.repositories;

import com.ingenera.lab1.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {

    @Query(value = "select o from Offer o where o.id is not null")
    List<Offer> getAllOffers();

}
