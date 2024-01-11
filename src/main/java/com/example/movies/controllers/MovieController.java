package com.example.movies.controllers;

import com.example.movies.models.Movies;
import com.example.movies.services.DynamoDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class MovieController {
    @Autowired
    private DynamoDBService dynamoDBService;

    @GetMapping
    public Iterable<Movies> getAllMovies(){
        return dynamoDBService.getAllMovies();
    }

    @GetMapping(path = "/findMovie")
    public ResponseEntity<?> findMovieByTitle(@RequestParam String title){
        List<Movies> movies = dynamoDBService.findMovieByTitle(title);

        if(movies != null){
            return ResponseEntity.ok().body(movies);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The movie title was not found.");

    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> findMovieById(@PathVariable Integer id){
        Movies movies = dynamoDBService.findMovieById(id);

        if(movies != null){
            return ResponseEntity.ok().body(movies);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The movie ID was not found.");
    }

    @PostMapping
    public ResponseEntity<?> saveMovie(@RequestBody Movies movies){
        dynamoDBService.saveMovie(movies);
       return ResponseEntity.ok().body(movies);
    }

}
