package com.ingenera.lab1.services.serviceimpl;

import com.ingenera.lab1.models.entities.Engins;
import com.ingenera.lab1.models.entities.Offer;
import com.ingenera.lab1.models.entities.Transmission;
import com.ingenera.lab1.repositories.OfferRepository;
import com.ingenera.lab1.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
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
        return this.offerRepository.getAllOffers();
    }
}
