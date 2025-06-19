package com.example.repositories;

import com.example.model.WeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherModel, Integer> {
}
