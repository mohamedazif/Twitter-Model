package com.twozo.twitter.services;

import com.twozo.twitter.entity.Retweet;
import com.twozo.twitter.entity.Tweet;
import com.twozo.twitter.entity.User;
import com.twozo.twitter.repository.TweetRepository;
import com.twozo.twitter.services.interfaces.ProfileService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public final class UserProfile implements ProfileService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * To show the profile of the User.
     * @param user Object of the logged-in user
     */
    public void showProfile(final User user) {
        System.out.println(
                "\nUserId: " + user.getUserId()
                        + "\nWelcome " + user.getUserName()
                        + " to the Twitter World"
                        + "\nBio: " + user.getBio()
                        + "\nFollowing: " + user.getFollowing().size()
                        + "\nFollowers: " + user.getFollowers().size()
                        + "\nMy Tweets:");

        List<CombinedTweets> ogNrtTweets = new ArrayList<>();

        for (final Tweet tweet : TweetRepository.
                getSpecificUserTweets(user.getUserId())) {
            ogNrtTweets.add(new CombinedTweets(tweet.getCreatedAt(),
                    tweet, false));
        }

        for (final Retweet retweet : user.getRetweets()) {
            ogNrtTweets.add(new CombinedTweets(retweet.getRetweetedAt(),
                    retweet.getOgTweet(), true));
        }

        ogNrtTweets.sort(Comparator.comparing(CombinedTweets::timeStamp).
                reversed());

        for (CombinedTweets tweets : ogNrtTweets) {
            printUserTweet(tweets);
        }

        SCANNER.nextLine();
        SCANNER.nextLine();
    }

    private void printUserTweet(final CombinedTweets tweetsList) {
        if (tweetsList.isRetweet) {
            System.out.println("\nYou Retweeted");
        }

        System.out.println("User-Id: " + tweetsList.tweet.getUserId() + "\n"
                + tweetsList.tweet.getTweetContent()
                + "\nLikes: " + tweetsList.tweet.getLikesCount()
                + "\tRetweets: " + tweetsList.tweet.getRetweetCount());
    }

    private record CombinedTweets(LocalDateTime timeStamp, Tweet tweet,
                                  boolean isRetweet) { }
}
