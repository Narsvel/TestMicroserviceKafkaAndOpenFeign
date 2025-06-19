package com.example.mapper;

import com.example.controller.client.model.WeatherApiResponse;
import com.example.model.WeatherModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeatherModelMapper {

    public WeatherModel mapToWeatherModel(Double longitude, Double latitude,
                                          LocalDateTime date, WeatherApiResponse weatherApiResponse) {
        return WeatherModel.builder()
                .longitude(longitude)
                .latitude(latitude)
                .date(date)
                .temperature(weatherApiResponse.value())
                .build();
    }
}
