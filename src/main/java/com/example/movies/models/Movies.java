package com.example.movies.models;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

@DynamoDbBean
public class Movies {
    private Integer id;
    private String title;
    private String director;
    private int year;
    private int minutes;
    private Double rate;
    private String status;
    private String imageUrl;

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

    public Movies(Integer id, String title, String director, int year, int minutes, Double rate, String status, String imageUrl) {
        this.id = id;
        this.title = title;
        this.director = director;
        this.year = year;
        this.minutes = minutes;
        this.rate = rate;
        this.status = status;
        this.imageUrl = imageUrl;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Movie Details:\n" +
                "\tID: " + id + "\n" +
                "\tTitle: " + title + "\n" +
                "\tDirector: " + director + "\n" +
                "\tYear: " + year + "\n" +
                "\tDuration: " + minutes + " minutes\n" +
                "\tRating: " + rate + "\n" +
                "\tStatus: " + status;
    }

}
