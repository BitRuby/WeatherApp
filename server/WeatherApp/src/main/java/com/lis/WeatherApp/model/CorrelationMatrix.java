package com.lis.WeatherApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;

@Document
public class CorrelationMatrix {
    @JsonIgnore
    private ArrayList<String> label;
    private ArrayList<String> labels;
    private double[][] matrix;

    public CorrelationMatrix() {
        this.labels = new ArrayList<String>( Arrays.asList("Year","Month","Day","Hour","pm2.5","Dew Point","TEMP","PRES","Wind","Snow","Rain"));
        this.label = new ArrayList<String>( Arrays.asList("year","month","day","hour","pm25","dewp","TEMP","PRES","Iws","Is","Ir"));
        this.matrix = new double[11][11];
    }

    public ArrayList<String> getLabel() {
        return label;
    }

    public void setLabel(ArrayList<String> label) {
        this.label = label;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<String> labels) {
        this.labels = labels;
    }
}
