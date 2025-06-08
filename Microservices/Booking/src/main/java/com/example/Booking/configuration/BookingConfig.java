package com.example.Booking.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BookingConfig {
    @Bean
    @LoadBalanced  // Yeh flag karta hai ki RestTemplate service discovery ke through call karega
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
