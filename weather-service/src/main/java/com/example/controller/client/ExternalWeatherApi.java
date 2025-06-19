package com.example.controller.client;

import com.example.controller.client.model.WeatherApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@FeignClient(name = "weather")
public interface ExternalWeatherApi {

    @GetMapping
    WeatherApiResponse getWeather(
            @RequestParam(name = "lon") double longitude,
            @RequestParam(name = "lat") double latitude,
            @RequestParam(name = "date") LocalDateTime date);
}
