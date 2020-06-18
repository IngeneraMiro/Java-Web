package com.ingenera.linkeduot.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConfigurations {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
