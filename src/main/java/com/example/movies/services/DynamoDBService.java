package com.example.movies.services;

import com.example.movies.exceptions.MovieNotFoundException;
import com.example.movies.models.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class DynamoDBService {

    private final DynamoDbTable<Movies> moviesTable;

    @Autowired
    public DynamoDBService(DynamoDbClient dynamoDbClient){

        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.moviesTable = enhancedClient.table("Movies", TableSchema.fromBean(Movies.class));
    }

    public Iterable<Movies> getAllMovies(){
        PageIterable<Movies> movies = moviesTable.scan();
        return movies.items().stream().collect(Collectors.toList());
    }

    public void saveMovie(Movies movie){
        moviesTable.putItem(movie);
    }

    public Movies findMovieById(Integer id){
        Key key = Key.builder()
                .partitionValue(id)
                .build();

        return Optional.ofNullable(moviesTable.getItem(key))
                .orElseThrow(MovieNotFoundException::new);
    }

    public List<Movies> findMovieByTitle(String title){
        DynamoDbIndex<Movies> titleIndex = moviesTable.index("title-index");

        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(title).build());

        SdkIterable<Page<Movies>> result = titleIndex.query(queryConditional);

        List<Movies> movies = new ArrayList<>();
        for (Page<Movies> page : result) {
            movies.addAll(page.items());
        }

        return movies;
    }

}
