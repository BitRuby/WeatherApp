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

    @Override
    public double[][] getRegression(String col1, String col2, int year) {
        List<Beijing>  beijings = beijingRepo.findByYear(year);
        if(beijings == null)
            System.out.println("xDDD");
        else {
            System.out.println(beijings.size());
            System.out.println(beijings.get(0).getNo());
        }
        double[][] data = new double[beijings.size()][2];
        for(int i = 0; i < beijings.size(); i++){
            if(col1.equals("No"))
                data[i][0] = beijings.get(i).getNo();
            if(col1.equals("year"))
                data[i][0] = beijings.get(i).getYear();
            if(col1.equals("month"))
                data[i][0] = beijings.get(i).getMonth();
            if(col1.equals("day"))
                data[i][0] = beijings.get(i).getDay();
            if(col1.equals("hour"))
                data[i][0] = beijings.get(i).getHour();
            if(col1.equals("pm25"))
                data[i][0] = beijings.get(i).getPm25();
            if(col1.equals("dewp"))
                data[i][0] = beijings.get(i).getDewp();
            if(col1.equals("TEMP"))
                data[i][0] = beijings.get(i).getTEMP();
            if(col1.equals("PRES"))
                data[i][0] = beijings.get(i).getPRES();
            if(col1.equals("Iws"))
                data[i][0] = beijings.get(i).getIws();
            if(col1.equals("Is"))
                data[i][0] = beijings.get(i).getIs();
            if(col1.equals("Ir"))
                data[i][0] = beijings.get(i).getIr();

            if(col2.equals("No"))
                data[i][1] = beijings.get(i).getNo();
            if(col2.equals("year"))
                data[i][1] = beijings.get(i).getYear();
            if(col2.equals("month"))
                data[i][1] = beijings.get(i).getMonth();
            if(col2.equals("day"))
                data[i][1] = beijings.get(i).getDay();
            if(col2.equals("hour"))
                data[i][1] = beijings.get(i).getHour();
            if(col2.equals("pm25"))
                data[i][1] = beijings.get(i).getPm25();
            if(col2.equals("dewp"))
                data[i][1] = beijings.get(i).getDewp();
            if(col2.equals("TEMP"))
                data[i][1] = beijings.get(i).getTEMP();
            if(col2.equals("PRES"))
                data[i][1] = beijings.get(i).getPRES();
            if(col2.equals("Iws"))
                data[i][1] = beijings.get(i).getIws();
            if(col2.equals("Is"))
                data[i][1] = beijings.get(i).getIs();
            if(col2.equals("Ir"))
                data[i][1] = beijings.get(i).getIr();
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
