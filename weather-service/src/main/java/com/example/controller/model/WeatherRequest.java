package com.example.controller.model;

import java.time.LocalDateTime;

public record WeatherRequest(Double longitude, Double latitude, LocalDateTime date) {
}
