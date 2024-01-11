package com.example.movies.services;

import com.example.movies.models.Movies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
@Service
public class SqsWatchlistService {
    @Value("${aws.sqs.watchlist-queue-url}")
    private String queue;
    @Autowired
    private SqsClient sqsClient;

    private static final Logger logger = LoggerFactory.getLogger(SqsWatchlistService.class);
    public void sendToQueue(Movies movie){
        try {
            sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queue)
                    .messageBody(movie.toString())
                    .build());
            logger.info("The message was send to the queue.");
        }catch (Exception e){
            logger.error("The message failed to be send to queue. ", e);
        }

    }

}
