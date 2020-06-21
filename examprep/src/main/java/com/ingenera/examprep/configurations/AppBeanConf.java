package com.ingenera.examprep.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConf {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }

}
