package com.twozo.twitter.repository;

import com.twozo.twitter.entity.Tweet;
import com.twozo.twitter.entity.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for storing and retrieving tweets.
 * Uses in-memory storage for all tweets in the application.
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public final class TweetRepository {

    private static final Collection<Tweet> ALL_TWEETS = new ArrayList<>();
    private static final Map<Integer, Tweet> MAPPED_TWEETS = new HashMap<>();

    private TweetRepository() { }

    /**
     * Adds a tweet to the global list of tweets.
     *
     * @param tweet the tweet to be added
     */
    public static void addTweet(final Tweet tweet) {
        ALL_TWEETS.add(tweet);
        MAPPED_TWEETS.put(tweet.getTweetId(), tweet);
    }

    /**
     * Retrieves all tweets posted by a specific user.
     *
     * @param userId the ID of the user
     * @return a collection of tweets by the user
     */
    public static Collection<Tweet> getSpecificUserTweets(final String userId) {
        return ALL_TWEETS.stream()
                .filter(tweet -> userId.equals(tweet.getUserId()))
                .toList();
    }

    /**
     * Retrieves all tweets for the logged-in user's timeline.
     * This includes tweets posted by users they follow.
     *
     * @param user the logged-in user
     * @return a collection of timeline tweets
     */
    public static Collection<Tweet> getTimelineTweets(final User user) {
        final Collection<Tweet> timelineTweets = new ArrayList<>();

        for (final Tweet tweet : ALL_TWEETS) {
            if (user.getFollowing().contains(tweet.getUserId())) {
                timelineTweets.add(tweet);
            }
        }

        return timelineTweets;
    }

    /**
     * To get Tweets using TweetID.
     *
     * @param tweetID Unique identification of the Tweet
     * @return Tweets mapped along with their Unique ID
     */
    public static Tweet getTweetByID(final int tweetID) {
        return MAPPED_TWEETS.get(tweetID);
    }
}
