package com.example.movies.models;

public class Movies {
    private String idMovie;
    private String title;
    private String director;
    private int minutes;
    private double rate;
    private boolean status;

    public Movies() {
    }

    public Movies(String idMovie, String title, String director, int minutes, double rate, boolean status) {
        this.idMovie = idMovie;
        this.title = title;
        this.director = director;
        this.minutes = minutes;
        this.rate = rate;
        this.status = status;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

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

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
