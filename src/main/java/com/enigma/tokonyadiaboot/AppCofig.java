package com.enigma.tokonyadiaboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppCofig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
