package com.example.facade;

import com.example.controller.model.WeatherRequest;
import com.example.controller.model.WeatherResponse;
import com.example.mapper.WeatherResponseMapper;
import com.example.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherFacade {

    private final WeatherService weatherService;
    private final WeatherResponseMapper weatherResponseMapper;

    public WeatherResponse getWeather(WeatherRequest request) {
        return weatherResponseMapper.mapToWeatherResponse(weatherService.
                getWeather(request.longitude(), request.latitude(), request.date()));
    }
}
