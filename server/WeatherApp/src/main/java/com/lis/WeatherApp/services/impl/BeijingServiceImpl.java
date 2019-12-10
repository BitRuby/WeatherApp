package com.lis.WeatherApp.services.impl;

import com.lis.WeatherApp.model.Beijing;
import com.lis.WeatherApp.repository.BeijingRepo;
import com.lis.WeatherApp.services.BeijingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeijingServiceImpl implements BeijingService {

    @Autowired
    private BeijingRepo beijingRepo;
    @Override
    public List<Beijing> findAll() {
        return beijingRepo.findAll();
    }

    @Override
    @Query("{ 'year' : { $gt: ?0, $lt: ?1 } }")
    public List<Beijing> findPartition(int yearGT, int yearLT) {
        return beijingRepo.findByYearBetween(yearGT,yearLT);
    }

    @Override
    public List<Beijing> findByYear(int year) {
        return beijingRepo.findByYear(year);
    }
}
