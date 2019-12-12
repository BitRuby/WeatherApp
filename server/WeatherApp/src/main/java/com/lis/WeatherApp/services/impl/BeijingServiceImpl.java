package com.lis.WeatherApp.services.impl;

import com.lis.WeatherApp.model.Beijing;
import com.lis.WeatherApp.repository.BeijingRepo;
import com.lis.WeatherApp.services.BeijingService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class BeijingServiceImpl implements BeijingService {

    @Autowired
    private BeijingRepo beijingRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
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

    @Override
    public List<Document> sumByMonthAndYear(int year, String what) {
        Aggregation agg = newAggregation(
                match(Criteria.where("year").is(year)),
                group("month").sum(what).as(what)
                        .last("year").as("year")
                        .last("month").as("month"),
                        sort(Direction.ASC,"month")
        );
        AggregationResults<Document> argResults = mongoTemplate.aggregate(
                agg, "beijing", Document.class);
        return argResults.getMappedResults();
    }

    @Override
    public List<Document> avgByMonthAndYear(int year, String what) {
        Aggregation agg = newAggregation(
                match(Criteria.where("year").is(year)),
                group("month").avg(what).as(what)
                        .last("year").as("year")
                        .last("month").as("month"),
                sort(Direction.ASC,"month")
        );
        AggregationResults<Document> argResults = mongoTemplate.aggregate(
                agg, "beijing", Document.class);
        return argResults.getMappedResults();
    }


}
