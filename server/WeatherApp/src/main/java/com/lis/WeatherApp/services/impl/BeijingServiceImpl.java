package com.lis.WeatherApp.services.impl;

import com.lis.WeatherApp.model.Beijing;
import com.lis.WeatherApp.model.CorrelationMatrix;
import com.lis.WeatherApp.model.CorrelationModel;
import com.lis.WeatherApp.repository.BeijingRepo;
import com.lis.WeatherApp.services.BeijingService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.lis.WeatherApp.utils.Utils.calculateCorrelation;
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
        return beijingRepo.findByYearBetween(yearGT, yearLT);
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
                sort(Direction.ASC, "month")
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
                sort(Direction.ASC, "month")
        );
        AggregationResults<Document> argResults = mongoTemplate.aggregate(
                agg, "beijing", Document.class);
        return argResults.getMappedResults();
    }

    @Override
    public double getCorrelation(String col1, String col2) {
        Aggregation agg = newAggregation(
                group()
                        .avg(col1).as("e1")
                        .avg(col2).as("e2")
                        .sum(ArithmeticOperators.Pow.valueOf(col1).pow(2)).as("xPow2")
                        .sum(ArithmeticOperators.Pow.valueOf(col2).pow(2)).as("yPow2")
                        .sum(ArithmeticOperators.Multiply.valueOf(col1).multiplyBy(col2)).as("xy")
                        .count().as("count")
        );
        CorrelationModel argResults = mongoTemplate.aggregate(
                agg, "beijing", CorrelationModel.class).getUniqueMappedResult();
        return calculateCorrelation(argResults);
    }

    @Override
    public CorrelationMatrix getAllCorrelations() {
        CorrelationMatrix cm = new CorrelationMatrix();
        ArrayList<String> labels = cm.getLabel();
        double[][] matrix = cm.getMatrix();
        for (int i = 0; i < labels.size(); i++) {
            for (int j = 0; j < labels.size(); j++) {

                Aggregation agg = newAggregation(
                        group()
                                .avg(labels.get(i)).as("e1")
                                .avg(labels.get(j)).as("e2")
                                .sum(ArithmeticOperators.Pow.valueOf(labels.get(i)).pow(2)).as("xPow2")
                                .sum(ArithmeticOperators.Pow.valueOf(labels.get(j)).pow(2)).as("yPow2")
                                .sum(ArithmeticOperators.Multiply.valueOf(labels.get(i)).multiplyBy(labels.get(j))).as("xy")
                                .count().as("count")
                );
                CorrelationModel argResults = mongoTemplate.aggregate(
                        agg, "beijing", CorrelationModel.class).getUniqueMappedResult();
                matrix[i][j] = calculateCorrelation(argResults);
            }
        }

        return cm;
    }

}
