package com.twozo.twitter.services;

import com.twozo.twitter.entity.Tweet;
import com.twozo.twitter.repository.TweetRepository;
import com.twozo.twitter.services.interfaces.TweetService;

import java.util.Scanner;

/**
 * Posts tweets created by the user.
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public final class PostTweet implements TweetService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Allows a user to post a new tweet.
     *
     * @param userID the ID of the logged-in user
     */
    public void postTweet(final String userID) {
        System.out.println("\nWrite your tweet thoughts:");
//        SCANNER.nextLine();
        final String tweetContent = SCANNER.nextLine();

        if (tweetContent == null || tweetContent.isBlank()) {
            System.err.println("Tweet content cannot be empty");
            return;
        }

        if (tweetContent.length() > 280) {
            System.err.println("Tweet cannot exceed 280 characters.");
            return;
        }

        final Tweet tweet = new Tweet(userID, tweetContent);
        TweetRepository.addTweet(tweet);
        System.out.println("Tweet posted successfully by " + tweet.getUserId());
    }
}
