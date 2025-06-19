package com.example.controller.model;

import java.time.LocalDateTime;

public record WeatherResponse(LocalDateTime date, String temperature) {
}
