package com.twozo.twitter.services;

import com.twozo.twitter.entity.Retweet;
import com.twozo.twitter.entity.Tweet;
import com.twozo.twitter.entity.User;
import com.twozo.twitter.repository.TweetRepository;
import com.twozo.twitter.services.interfaces.TimelineService;

import java.util.Scanner;

public final class UserTimeline implements TimelineService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Show tweets of in the User Timeline.
     * @param user User who is logged in
     */
    public void showTimeLine(final User user) {
        System.out.println("Timeline of " + user.getUserId());

        for (final Tweet tweet : TweetRepository.getTimelineTweets(user)) {
            System.out.println("User-Id: " + tweet.getUserId() + "\n"
                    + tweet.getTweetContent()
                    + "\nLikes: " + tweet.getLikesCount() + "\tRetweets: "
                    + tweet.getRetweetCount()
                    + "\nEnter 1 to Like\t\t2 to Retweet");
            int choice = SCANNER.nextInt();
            if (choice == 1) {
                tweet.addLike();
            } else if (choice == 2) {
                tweet.addRTCount();
                user.getRetweets().add(new Retweet(tweet, tweet.getUserId()));
                System.out.println("Retweeted Successfully!");
            }
        }
    }
}
