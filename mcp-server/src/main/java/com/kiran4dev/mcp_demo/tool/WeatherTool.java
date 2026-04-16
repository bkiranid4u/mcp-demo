package com.kiran4dev.mcp_demo.tool;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.resilience.annotation.ConcurrencyLimit;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

import com.kiran4dev.mcp_demo.tool.models.WeatherResponse;

@Component
public class WeatherTool {

    @Qualifier("weathRestClient")
    private final RestClient weatherRestClient;

    public WeatherTool(RestClient weatherRestClient) {
        this.weatherRestClient = weatherRestClient;
    }

    @McpTool(name = "get_weather", description = "Fetches weather information for a given location")
    @Retryable(
        includes = {ResourceAccessException.class, HttpServerErrorException.class}, 
        maxRetries = 3,            // Number of retries AFTER the first attempt (Total: 4)
        delayString = "1000ms",    // Initial wait time
        multiplier = 2.0,          // Exponential backoff: 1s, 2s, 4s
        maxDelay = 10000           // Caps the delay at 10 seconds
    )
    @ConcurrencyLimit(limit = 20) // Native Bulkhead: caps concurrent threads to 20
    public String getWeather(@McpToolParam(description = "Location to fetch weather for") String location) {
        // Placeholder for actual API call using weatherRestClient
        var weather = this.weatherRestClient.get()
                .uri("/forecast?latitude=35&longitude=139&hourly=temperature_2m")
                .retrieve()
                .body(WeatherResponse.class);
        return "Weather data for " + location;
    }

    // https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m&current=temperature_2m&start_date=2026-04-09&end_date=2026-04-23

}
