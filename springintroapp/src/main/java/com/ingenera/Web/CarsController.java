package com.ingenera.Web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsController {

    @RequestMapping("/")
    public String[] getCars(){
        return new String[]{"Black","Red","White"};
    }
}
