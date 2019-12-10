package com.lis.WeatherApp.repository;

import com.lis.WeatherApp.model.Beijing;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BeijingRepo extends MongoRepository<Beijing,String> {
    List<Beijing> findByYearBetween(int g, int l);
    List<Beijing> findByYear(int year);
}
