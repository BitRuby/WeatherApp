package com.lis.WeatherApp.services;

import com.lis.WeatherApp.model.Beijing;

import java.util.List;

public interface BeijingService {
    List<Beijing> findAll();

    List<Beijing> findPartition(int yearGT, int yearLT);

    List<Beijing> findByYear(int year);

}
