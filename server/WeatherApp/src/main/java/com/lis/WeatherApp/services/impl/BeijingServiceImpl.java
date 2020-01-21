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
import java.util.List;

import static com.lis.WeatherApp.utils.Utils.calculateCorrelation;
import static com.lis.WeatherApp.utils.Utils.changeToArray;
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
                group("month")
                         .sum(what).as(what)
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
        int y = 0;
        int x = 0;
        for (int i = 0; i < labels.size() * 11; i++) {
            for (int j = 0; j < 3 ; j++) {
                if(y == 11){
                    y = 0;
                    x++;
                }

                if(j == 0)
                    matrix[i][j] = x;

                if(j == 1){
                    matrix[i][j] = y;
                }

                if(j == 2){
                    Aggregation agg = newAggregation(
                            group()
                                    .avg(labels.get(x)).as("e1")
                                    .avg(labels.get(y)).as("e2")
                                    .sum(ArithmeticOperators.Pow.valueOf(labels.get(x)).pow(2)).as("xPow2")
                                    .sum(ArithmeticOperators.Pow.valueOf(labels.get(y)).pow(2)).as("yPow2")
                                    .sum(ArithmeticOperators.Multiply.valueOf(labels.get(x)).multiplyBy(labels.get(y))).as("xy")
                                    .count().as("count")
                    );
                    CorrelationModel argResults = mongoTemplate.aggregate(
                            agg, "beijing", CorrelationModel.class).getUniqueMappedResult();
                    matrix[i][j] = calculateCorrelation(argResults);
                    y++;
                }

            }
        }
        return cm;
    }

    @Override
    public double[][] getRegression(String col1, String col2, int year) {
        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query().addCriteria(Criteria.where("year").is(year));
        query.fields().include(col1).include(col2).exclude("_id");
        List<Document> documentList = mongoTemplate.find(query, Document.class, "beijing");

        double[][] data = new double[documentList.size()][2];
        for(int i = 0; i < documentList.size(); i++) {
            if (documentList.get(i).get(col1).getClass() == Integer.class)
                data[i][0] = (int)documentList.get(i).get(col1);
            else
                data[i][0] = (double)documentList.get(i).get(col1);

            if (documentList.get(i).get(col2).getClass() == Integer.class)
                data[i][1] = (int)documentList.get(i).get(col2);
            else
                data[i][1] = (double)documentList.get(i).get(col2);
        }
        return data;
    }

    @Override
    public double[][] getBoxes(String col, int year) {
        ArrayList<Integer> monthList = new ArrayList<Integer>();
        ArrayList<Double> dataList = new ArrayList<Double>();

        org.springframework.data.mongodb.core.query.Query query = new org.springframework.data.mongodb.core.query.Query().addCriteria(Criteria.where("year").is(year));
        query.fields().include("month").include(col).exclude("_id");
        List<Document> documentList = mongoTemplate.find(query, Document.class, "beijing");

        documentList.forEach(document -> {
            monthList.add((int)document.get("month"));
            if (document.get(col).getClass() == Integer.class) {
                Integer d = (Integer) document.get(col);
                dataList.add(d.doubleValue());
            } else {
                dataList.add((double) document.get(col));
            }
        });
        System.out.println("size of list " + monthList.size() + "  " + dataList.size());
        return changeToArray(monthList, dataList);
    }


}
