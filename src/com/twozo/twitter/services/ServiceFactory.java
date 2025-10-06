package com.twozo.twitter.services;

import com.twozo.twitter.services.interfaces.*;

/**
 * To manage object creation in a same place.
 * @version             25 Sept 2025
 * @author              Mohamed Azif
 */
public final class ServiceFactory {
    private ServiceFactory() { }

    /**
     * Creates UserRegister Object.
     * @return Object of UserRegister
     */
    public static RegisterService getRegisterService() {
        return new UserRegister();
    }

    /**
     * Creates UserLogin Object.
     * @return Object of UserLogin
     */
    public static LoginService getLoginService() {
        return new UserLogin();
    }

    /**
     * Creates UserTimeLine object.
     * @return Object of UserTimeLine
     */
    public static TimelineService getTimelineService() {
        return new UserTimeline();
    }

    /**
     * Creates UserProfile Object.
     * @return Object of UserProfile
     */
    public static ProfileService getProfileService() {
        return new UserProfile();
    }

    /**
     * Creates PostTweet Object.
     * @return Object of PostTweet
     */
    public static TweetService getTweetService() {
        return new PostTweet();
    }

    /**
     * Creates FollowSuggestion Object.
     * @return Object of FollowSuggestion
     */
    public static FollowSuggestionService getFollowSuggestionService() {
        return new FollowSuggestion();
    }

    /**
     * Creates object for EditProfile.
     * @return Object of ProfileEdit
     */
    public static EditService getEditProfileService() {
        return new ProfileEdit();
    }
}
