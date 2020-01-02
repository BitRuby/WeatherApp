package com.lis.WeatherApp.controllers;

import com.lis.WeatherApp.model.Beijing;
import com.lis.WeatherApp.model.CorrelationMatrix;
import com.lis.WeatherApp.services.BeijingService;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/beijing")
public class BeijingController {

    @Autowired
    private BeijingService beijingService;

    @RequestMapping(value = "/beijing", method = RequestMethod.GET)
    public List<Beijing> getAllRecords() {
        return beijingService.findAll();
    }
    @RequestMapping(value = "/beijing/year", method = RequestMethod.GET)
    public List<Beijing> getOneYear(@RequestParam(required = true) int year) {
        return beijingService.findByYear(year);
    }

    @RequestMapping(value = "/beijing/yearsbetween", method = RequestMethod.GET)
    public List<Beijing> getSelected(@RequestParam(required = true) int gr, @RequestParam(required = true) int low) {
        return beijingService.findPartition(gr,low);
    }
    @RequestMapping(value = "/beijing/sumByMonthAndYear", method = RequestMethod.GET)
    public List<Document> getSumInMonth(@RequestParam(required = true) int year, @RequestParam(required = true) String what) {
        return beijingService.sumByMonthAndYear(year,what);
    }

    @RequestMapping(value = "/beijing/avgByMonthAndYear", method = RequestMethod.GET)
    public List<Document> getAvgInMonth(@RequestParam(required = true) int year, @RequestParam(required = true) String what) {
        return beijingService.avgByMonthAndYear(year,what);
    }
    @RequestMapping(value = "/beijing/correlation", method = RequestMethod.GET)
    public double getCorrelation(@RequestParam(required = true) String col1, @RequestParam(required = true) String col2) {
        return beijingService.getCorrelation(col1,col2);
    }
    @RequestMapping(value = "/beijing/correlationMatrix", method = RequestMethod.GET)
    public CorrelationMatrix getCorrelation() {
        return beijingService.getAllCorrelations();
    }
}
