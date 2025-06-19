package com.example.service;

import com.example.controller.client.ExternalWeatherApi;
import com.example.controller.client.model.WeatherApiResponse;
import com.example.model.WeatherModel;
import com.example.repositories.WeatherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class WeatherService {

    private final WeatherRepository weatherRepository;
    private final ExternalWeatherApi externalWeatherApi;

    public WeatherService(WeatherRepository weatherRepository, ExternalWeatherApi externalWeatherApi) {
        this.weatherRepository = weatherRepository;
        this.externalWeatherApi = externalWeatherApi;
    }

    public WeatherModel getWeather(Double longitude,
                                                Double latitude,
                                                LocalDateTime date) {
        WeatherApiResponse weatherApiResponse = externalWeatherApi.getWeather(longitude, latitude, date);
        WeatherModel weatherModel = WeatherModel.builder()
                .longitude(longitude)
                .latitude(latitude)
                .date(date)
                .temperature(weatherApiResponse.value())
                .build();
        return weatherRepository.save(weatherModel);
    }
}
