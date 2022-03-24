package com.myschool.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    //TODO: cambiar al WebFlux
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
