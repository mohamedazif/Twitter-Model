package com.twozo.twitter.services;

import com.twozo.twitter.entity.User;
import com.twozo.twitter.repository.UserRepository;
import com.twozo.twitter.services.interfaces.FollowSuggestionService;

import java.util.Map;
import java.util.Scanner;

/**
 * Shows list of Users, users can choose who want to follow.
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public final class FollowSuggestion implements FollowSuggestionService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * To suggest the logged-in User to follow some friends.
     * @param user logged-in User object
     */
    public void suggestFollowers(final User user) {
        System.out.println("Follow Suggestions for " + user.getUserName());
        final Map<String, User> usersList = UserRepository.getUsersList();

        for (final User suggUser : usersList.values()) {
            if (!suggUser.getUserId().equals(user.getUserId())
                    && !user.getFollowing().contains(suggUser.getUserId())) {
                System.out.println(suggUser.getUserId()
                        + " Followers: " + suggUser.getFollowers().size()
                        + "\tFollowing: " + suggUser.getFollowing().size()
                        + "\nEnter 1 to Follow:");
                int doFollow = SCANNER.nextInt();

                if (doFollow == 1) {
                    user.getFollowing().add(suggUser.getUserId());
                    suggUser.getFollowers().add(user.getUserId());
                    System.out.println("You are now following " + suggUser.getUserId());
                }
            }
        }
    }
}
