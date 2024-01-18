package com.example.movies.controllers;

import com.example.movies.models.Movies;
import com.example.movies.services.DynamoDBService;
import com.example.movies.services.S3Service;
import com.example.movies.services.SqsWatchedService;
import com.example.movies.services.SqsWatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class MovieController {
    @Autowired
    private DynamoDBService dynamoDBService;
    @Autowired
    private SqsWatchlistService sqsWatchlistService;
    @Autowired
    private SqsWatchedService sqsWatchedService;
    @Autowired
    private S3Service s3Service;

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
    public ResponseEntity<?> saveMovie(@RequestBody Movies movie){
        dynamoDBService.saveMovie(movie);

        if(movie.getStatus().equalsIgnoreCase("watchlist")){
            sqsWatchlistService.sendToQueue(movie);
        }else if (movie.getStatus().equalsIgnoreCase("watched")){
            sqsWatchedService.sendMessage(movie);
        }

       return ResponseEntity.ok().body(movie);
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadMoviePoster(@PathVariable Integer id, @RequestParam("file") MultipartFile file){
        try{
            Movies movie = dynamoDBService.findMovieById(id);
            String imageUrl = s3Service.uploadPoster(file);

            if(movie == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found.");
            }

            movie.setImageUrl(imageUrl);
            dynamoDBService.update(id,movie);
            return ResponseEntity.status(HttpStatus.CREATED).body("The movie poster was uploaded. URL: " + imageUrl);

        }catch (Exception e ){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Movies movie) {
        Movies updatedMovie = dynamoDBService.update(id, movie);

        if ("watched".equalsIgnoreCase(movie.getStatus())) {
            sqsWatchedService.sendMessage(updatedMovie);

        } else if ("watchlist".equalsIgnoreCase(movie.getStatus())) {
            sqsWatchlistService.sendToQueue(updatedMovie);
        }

        return ResponseEntity.ok().body("The movie was updated.");
    }



}
