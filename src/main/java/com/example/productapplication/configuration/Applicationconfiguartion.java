package com.example.productapplication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Applicationconfiguartion {
    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
