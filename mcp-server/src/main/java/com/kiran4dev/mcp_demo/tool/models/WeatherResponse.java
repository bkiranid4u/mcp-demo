package com.kiran4dev.mcp_demo.tool.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponse(
    double latitude,
    double longitude,
    double elevation,
    @JsonProperty("generationtime_ms") double generationTimeMs,
    @JsonProperty("utc_offset_seconds") int utcOffsetSeconds,
    String timezone,
    @JsonProperty("timezone_abbreviation") String timezoneAbbreviation,
    HourlyData hourly,
    @JsonProperty("hourly_units") HourlyUnits hourlyUnits
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record HourlyData(
        List<String> time,
        @JsonProperty("temperature_2m") List<Double> temperature2m
    ) {}

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record HourlyUnits(
        @JsonProperty("temperature_2m") String temperature2m
    ) {}
}


