package com.example.movies.listeners;

import com.example.movies.models.Movies;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class SqsWatchlistListener {
    Logger logger = LoggerFactory.getLogger(SqsWatchlistListener.class);
    @SqsListener("${aws.sqs.watchlist-queue-url}")
    public void receiveMessage(Movies movie){
        logger.info("The movie was received in the queue: " + movie.toString());
    }

}
