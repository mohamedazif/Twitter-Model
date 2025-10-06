package com.twozo.twitter.services.interfaces;

import com.twozo.twitter.entity.User;

public interface FollowSuggestionService {
    void suggestFollowers(User user);
}
