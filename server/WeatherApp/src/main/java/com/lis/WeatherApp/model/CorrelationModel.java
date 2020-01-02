package com.lis.WeatherApp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CorrelationModel {
    private double e1; // wartość średnia (oczekiwana)
    private double e2;
    private double xPow2;
    private double yPow2;
    private double xy;
    private double count;

    public CorrelationModel() {
    }

    public double getE1() {
        return e1;
    }

    public void setE1(double e1) {
        this.e1 = e1;
    }

    public double getE2() {
        return e2;
    }

    public void setE2(double e2) {
        this.e2 = e2;
    }

    public double getxPow2() {
        return xPow2;
    }

    public void setxPow2(double xPow2) {
        this.xPow2 = xPow2;
    }

    public double getyPow2() {
        return yPow2;
    }

    public void setyPow2(double yPow2) {
        this.yPow2 = yPow2;
    }

    public double getXy() {
        return xy;
    }

    public void setXy(double xy) {
        this.xy = xy;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CorrelationModel{" +
                "e1=" + e1 +
                ", e2=" + e2 +
                ", xPow2=" + xPow2 +
                ", yPow2=" + yPow2 +
                ", xy=" + xy +
                ", count=" + count +
                '}';
    }
}
