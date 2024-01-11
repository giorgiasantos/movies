package com.example.movies.listeners;

import com.example.movies.models.Movies;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Component
public class SqsWatchedListener {
    Logger logger = LoggerFactory.getLogger(SqsWatchedListener.class);

    @SqsListener("${aws.sqs.watched-queue-url}")
    public void receiveMessage(Movies movie){
        logger.info("The movie was received in the queue: " + movie.toString());
    }

}
