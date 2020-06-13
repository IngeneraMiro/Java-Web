package com.ingenera.labessentials.services;

import com.ingenera.labessentials.models.entities.Offer;

import java.util.List;

public interface OfferService {

    List<Offer> getAllOffers();
    Long countAllOffers();

}
