package com.lis.WeatherApp.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Beijing {
    private int no;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int pm25;
    private int dewp;
    private int TEMP;
    private float PRES;
    private float Iws;
    private int Is;
    private int Ir;

    public Beijing() {
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getDewp() {
        return dewp;
    }

    public void setDewp(int dewp) {
        this.dewp = dewp;
    }

    public int getTEMP() {
        return TEMP;
    }

    public void setTEMP(int TEMP) {
        this.TEMP = TEMP;
    }

    public float getPRES() {
        return PRES;
    }

    public void setPRES(float PRES) {
        this.PRES = PRES;
    }

    public float getIws() {
        return Iws;
    }

    public void setIws(float iws) {
        Iws = iws;
    }

    public int getIs() {
        return Is;
    }

    public void setIs(int is) {
        Is = is;
    }

    public int getIr() {
        return Ir;
    }

    public void setIr(int ir) {
        Ir = ir;
    }
}
