package com.lis.WeatherApp.utils;

import com.lis.WeatherApp.model.CorrelationModel;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

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
    public static double[][] changeToArray(List month, List data){
        double[][] dataMatrix = new double[12][data.size()/12];
        int monthIndex = 1;
        int j = 0;
        for(int i = 0; i < data.size(); i++){
            if(j == data.size()/12)
                j = 0;

            if(monthIndex == (int)month.get(i)){
                dataMatrix[monthIndex -1][j] = (double)data.get(i);
            }else {
                ++monthIndex;
                dataMatrix[monthIndex -1][j] = (double)data.get(i);
            }
            j++;
        }
        //TODO jakies zera sie robia
        int d =0;
        for(int x = 0 ; x< 12; x++){
            for(int y = 0 ; y < data.size()/12; y++){
                if(dataMatrix[x][y] ==0){
                    d++;
                    System.out.println("KURWA" + d);
                }

            }
        }
        return dataMatrix;
    }

}
