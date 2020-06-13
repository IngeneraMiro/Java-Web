package com.ingenera.labessentials.web;

import com.ingenera.labessentials.models.entities.Brand;
import com.ingenera.labessentials.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    public ModelAndView allBrands(ModelAndView modelAndView){
        modelAndView.setViewName("brands");
        List<Brand> allBrands = new ArrayList<>();
        if(this.brandService.getBrandsCount()>0){
            allBrands.addAll(this.brandService.getAllBrands());
        }
        modelAndView.addObject("brands",allBrands);
        return modelAndView;
    }

}
