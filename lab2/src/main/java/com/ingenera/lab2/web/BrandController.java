package com.ingenera.lab2.web;

import com.ingenera.lab2.models.bindmodels.BrandBindModel;
import com.ingenera.lab2.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String brand() {
        return "brand-add";
    }

    @PostMapping("/add")
    public String addBrand(@Valid @ModelAttribute("brand") BrandBindModel brandBindModel, Error error) {
        System.out.println(this.brandService.addBrand(brandBindModel));
        return "brand-add";
    }

}
