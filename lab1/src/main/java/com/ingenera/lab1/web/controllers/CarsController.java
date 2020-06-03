package com.ingenera.lab1.web.controllers;

import com.ingenera.lab1.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cars")
public class CarsController {
    private final OfferService offerService;

    @Autowired
    public CarsController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ModelAndView allCars(ModelAndView modelAndView){
        modelAndView.addObject("offers",this.offerService.getAllOffers());
        modelAndView.setViewName("all");

        return (modelAndView);
    }

}
