package com.ingenera.labessentials.services.serviceImpl;


import com.ingenera.labessentials.models.entities.Offer;
import com.ingenera.labessentials.repositories.OfferRepository;
import com.ingenera.labessentials.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerRepository.getOffers();
    }

    @Override
    public Long countAllOffers() {
        return this.offerRepository.count();
    }
}
