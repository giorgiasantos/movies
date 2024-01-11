//package com.example.movies;
//
//import com.example.movies.models.Movies;
//import com.example.movies.services.DynamoDBService;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//@Component
//public class TesteRunner implements CommandLineRunner {
//    private final DynamoDBService dynamoDBService;
//    private static final Logger logger = LoggerFactory.getLogger(TesteRunner.class);
//    public TesteRunner(DynamoDBService dynamoDBService) {
//        this.dynamoDBService = dynamoDBService;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        doSomethingWhenStartup();
//    }
//
//    private void doSomethingWhenStartup() {
//
//        Movies movie = new Movies(1,"Killers of The Flower Moon","Martin Scorsese",2023,300,"watchlist");
//
//        try {
////            dynamoDBService.saveMovie(movie);
////            dynamoDBService.getAllMovies();
////            dynamoDBService.findMovieById(1);
////            dynamoDBService.findMovieByTitle("Killers of The Flower Moon");
//            logger.info("The movie was added in the database.");
//        }catch (Exception e){
//            logger.error("Movie failed to be added in database." , e);
//        }
//
//    }
//}
