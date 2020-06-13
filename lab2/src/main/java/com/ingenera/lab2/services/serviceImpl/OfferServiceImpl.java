package com.ingenera.lab2.services.serviceImpl;

import com.ingenera.lab2.repositories.OfferRepository;
import com.ingenera.lab2.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }
}
