package com.ingenera.springintroapp.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements com.ingenera.springintroapp.Services.CarService {
    @Override
    public String[] getAllCars() {
        return new String[]{"Black","White","Red"};
    }
}
