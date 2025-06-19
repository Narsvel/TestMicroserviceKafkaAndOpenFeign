package com.example.service;

import com.example.controller.client.ExternalWeatherApi;
import com.example.controller.client.model.WeatherApiResponse;
import com.example.mapper.WeatherModelMapper;
import com.example.model.WeatherModel;
import com.example.repositories.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@AllArgsConstructor
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final ExternalWeatherApi externalWeatherApi;
    private final WeatherModelMapper weatherModelMapper;

    public WeatherModel getWeather(Double longitude,
                                                Double latitude,
                                                LocalDateTime date) {
        WeatherApiResponse weatherApiResponse = externalWeatherApi.getWeather(longitude, latitude, date);
        return weatherRepository.save(weatherModelMapper
                .mapToWeatherModel(longitude, latitude, date, weatherApiResponse));
    }
}
