package com.lis.WeatherApp.services;

import com.lis.WeatherApp.model.Beijing;
import org.bson.Document;

import java.util.List;

public interface BeijingService {
    List<Beijing> findAll();

    List<Beijing> findPartition(int yearGT, int yearLT);

    List<Beijing> findByYear(int year);

    List<Document> sumByMonthAndYear(int year, String what);

    List<Document> avgByMonthAndYear(int year, String what);

}
