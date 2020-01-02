package com.lis.WeatherApp.utils;

import com.lis.WeatherApp.model.CorrelationModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Utils {
    public static double calculateCorrelation(CorrelationModel cl) {
//        System.out.println(cl.toString());
        double cov = cl.getXy() / cl.getCount() - cl.getE1() * cl.getE2() ;
        double sig1 =  Math.sqrt(cl.getxPow2()  / cl.getCount() - cl.getE1() * cl.getE1());
        double sig2 =  Math.sqrt(cl.getyPow2()  / cl.getCount() - cl.getE2() * cl.getE2());

        return round(cov / sig1 / sig2);
    }

    public static double round(double value){
        value = Math.round(value*10000000)/10000000.0d;
//        DecimalFormat df = new DecimalFormat("##.#######");
//        df.setRoundingMode(RoundingMode.CEILING);
//
//        return Double.parseDouble(df.format(value));
        return value;
    }

}
