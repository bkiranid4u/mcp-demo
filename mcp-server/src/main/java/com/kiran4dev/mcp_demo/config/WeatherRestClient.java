package com.kiran4dev.mcp_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class WeatherRestClient {

    @Bean
    public RestClient weathRestClient() {
        return RestClient.builder()
                .baseUrl("https://api.open-meteo.com/v1")
                .build();
    }
}
