package com.ingenera.labessentials.web;

import com.ingenera.labessentials.models.entities.Offer;
import com.ingenera.labessentials.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final OfferService offerService;

    @Autowired
    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping
    public ModelAndView home(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.setViewName("offers");
        List<Offer> offers = new ArrayList<>();
        if(offerService.countAllOffers()>0){
            offers.addAll(offerService.getAllOffers());
        }
        modelAndView.addObject("offers", offers);
        System.out.println(request.getSession().getAttribute("name"));
        return modelAndView;
    }
}
