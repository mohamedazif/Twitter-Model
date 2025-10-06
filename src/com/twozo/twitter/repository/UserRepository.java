package com.twozo.twitter.repository;

import com.twozo.twitter.entity.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for storing and managing registered users.
 * Uses an in-memory map where userId is the key.
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public final class UserRepository {

    /**
     * Contains all registered users.
     */
    private static final Map<String, User> USERS_LIST = new HashMap<>();

    private UserRepository() { }
//    private enum RegistrationStatus {
//        SUCCESS, DUPLICATE_USER_ID, DUPLICATE_EMAIL_ID
//    }

    /**
     * Adds a new user to the repository after validating uniqueness
     * of userId and email.
     *
     * @param userId unique identifier for the user
     * @param user   the user object to register
     * @return true if registration is successful; false otherwise
     */
    public static boolean addRegisteredUser(final String userId,
                                            final User user) {
        // Check if userId is already in use
        if (USERS_LIST.containsKey(userId)) {
            System.err.println("User ID already exists!");
            return false;
        }

        // Check if email is already in use
        for (User u : USERS_LIST.values()) {
            if (u.getEmail().equalsIgnoreCase(user.getEmail())) {
                System.err.println("Email is already registered!");
                return false;
            }
        }

        // Both are unique register the user
        USERS_LIST.put(userId, user);
        return true;
    }

    /**
     * Static method to get a User who has the specific UserID.
     *
     * @param userId To retrieve the User with this UserID
     * @return Returns User Object if UserID is found else null
     */
    public static User getSpecificUser(final String userId) {
        return USERS_LIST.get(userId);
    }

    /**
     * Static method to return all the registered Users.
     *
     * @return Returns a hashmap of Users where UserID is mapped to User Object
     */
    public static Map<String, User> getUsersList() {
        return Collections.unmodifiableMap(USERS_LIST);
    }
}
