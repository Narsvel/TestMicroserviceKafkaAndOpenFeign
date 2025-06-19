package com.example.facade;

import com.example.controller.model.WeatherRequest;
import com.example.controller.model.WeatherResponse;
import com.example.mapper.WeatherResponseMapper;
import com.example.service.WeatherService;
import org.springframework.stereotype.Service;

@Service
public class WeatherFacade {

    private final WeatherService weatherService;
    private final WeatherResponseMapper weatherResponseMapper;

    public WeatherFacade(WeatherService weatherService, WeatherResponseMapper weatherResponseMapper) {
        this.weatherService = weatherService;
        this.weatherResponseMapper = weatherResponseMapper;
    }

    public WeatherResponse getWeather(WeatherRequest request) {
        return weatherResponseMapper.mapToWeatherResponse(weatherService.
                getWeather(request.longitude(), request.latitude(), request.date()));
    }
}
