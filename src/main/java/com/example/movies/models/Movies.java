package com.example.movies.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
public class Movies {
    private Integer id;
    private String title;
    private String director;
    private int year;
    private int minutes;
    private Double rate;
    private String status;

    public Movies() {
    }

    public Movies(Integer id, String title, String director, int year, int minutes, String status) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.minutes = minutes;
        this.status = status;
    }

    public Movies(Integer id, String title, String director, int year, int minutes, double rate, String status) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.minutes = minutes;
        this.rate = rate;
        this.status = status;
    }

    @DynamoDbPartitionKey
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @DynamoDbSecondaryPartitionKey(indexNames = {"title-index"})
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @DynamoDbSortKey
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
