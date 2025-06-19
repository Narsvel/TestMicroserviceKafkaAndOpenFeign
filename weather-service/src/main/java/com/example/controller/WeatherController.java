package com.example.controller;

import com.example.controller.model.WeatherRequest;
import com.example.controller.model.WeatherResponse;
import com.example.facade.WeatherFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {

    private final WeatherFacade weatherFacade;

    @PostMapping
    public WeatherResponse getWeather(@RequestBody final WeatherRequest request) {
        return weatherFacade.getWeather(request);
    }

}
