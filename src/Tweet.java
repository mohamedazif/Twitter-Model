import java.util.ArrayList;
import java.util.Collection;

final class Utils {

    private static int cmtId = 1;
    private static int tweetId = 1;

    public static int nextTweetId() {
        return tweetId++;
    }

    public static int nextCmtId() {
        return cmtId++;
    }
}

public class Tweet {

    private final int tweetId;
    private final String userId;
    private String tweetContent;
    private int likesCount;
    private int retweetCount;
    private static final Collection<Tweet> allTweets = new ArrayList<>();

    Tweet(String userId, String tweetContent) {
        this.tweetId = Utils.nextTweetId();
        this.userId = userId;
        this.tweetContent = tweetContent;
        this.likesCount = 0;
        this.retweetCount = 0;
        allTweets.add(this);
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

    protected static Collection<Tweet> getSpecificUserTweets(String userId) {
        Collection<Tweet> userTweets = new ArrayList<>();

        for (Tweet tweet : allTweets) {
            if (userId.equals(tweet.userId)) {
                userTweets.add(tweet);
            }
        }

        return userTweets;
    }

    protected static Collection<Tweet> getTimelineTweets(User user) {
        Collection<Tweet> timelineTweets = new ArrayList<>();

        for (Tweet tweet : allTweets) {
            if (user.getFollowing().contains(tweet.userId)) {
                timelineTweets.add(tweet);
            }
        }

        return timelineTweets;
    }

    static class Comment {
        int cmtId;
        int twtId;
        String cmtContent;

        Comment(int twtId, String cmtContent) {
            this.cmtId = Utils.nextCmtId();
            this.twtId = twtId;
            this.cmtContent = cmtContent;
        }
    }

}