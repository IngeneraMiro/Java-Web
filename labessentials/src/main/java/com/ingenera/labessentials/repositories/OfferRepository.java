package com.ingenera.labessentials.repositories;


import com.ingenera.labessentials.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    @Query(value = "select o from Offer as o where o.id is not null")
    List<Offer> getOffers();

    List<Offer> getAllByIdIsNotNull();
}
