package com.twozo.twitter.entity;

import com.twozo.twitter.services.Utility;

import java.time.LocalDateTime;

/**
 * Represents a Tweet posted by a user.
 * Contains tweet content, author information, and counts for
 * likes and retweets.
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public class Tweet {

    private final int tweetId;
    private final String userId;
    private final String tweetContent;
    private final LocalDateTime createdAt;
    private int likesCount;
    private int retweetCount;

    /**
     * Creates a new Tweet with a unique ID.
     *
     * @param userId        the ID of the user who posted the tweet
     * @param tweetContent  the content of the tweet
     */
    public Tweet(final String userId, final String tweetContent) {
        this.tweetId = Utility.nextTweetId();
        this.userId = userId;
        this.tweetContent = tweetContent;
        this.createdAt = LocalDateTime.now();
        this.likesCount = 0;
        this.retweetCount = 0;
    }

    public int getTweetId() {
        return tweetId;
    }

    public String getUserId() {
        return userId;
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void addLike() {
        likesCount++;
    }

    public void addRTCount() {
        retweetCount++;
    }
}
