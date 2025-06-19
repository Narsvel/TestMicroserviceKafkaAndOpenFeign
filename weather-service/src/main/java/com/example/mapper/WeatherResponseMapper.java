package com.example.mapper;

import com.example.controller.model.WeatherResponse;
import com.example.model.WeatherModel;
import org.springframework.stereotype.Component;

@Component
public class WeatherResponseMapper {

    public WeatherResponse mapToWeatherResponse(WeatherModel weatherModel) {
        return new WeatherResponse(weatherModel.getDate(), weatherModel.getTemperature());
    }

}
