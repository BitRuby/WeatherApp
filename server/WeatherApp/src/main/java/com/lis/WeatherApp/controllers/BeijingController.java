package com.lis.WeatherApp.controllers;

import com.lis.WeatherApp.model.Beijing;
import com.lis.WeatherApp.services.BeijingService;

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
}
