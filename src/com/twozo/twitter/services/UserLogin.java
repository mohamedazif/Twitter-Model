package com.twozo.twitter.services;

import com.twozo.twitter.entity.User;
import com.twozo.twitter.repository.UserRepository;
import com.twozo.twitter.services.interfaces.LoginService;

import java.util.Scanner;

/**
 * Checks credentials and logs in the user.
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public final class UserLogin implements LoginService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * To check login of the users.
     * @return User object if produced UserId and Password matches the
     *          registered User's UserId and Password else null
     */
    public User loginUser() {
        System.out.println("Enter your User-Id:");
        final String userId = SCANNER.next();
        System.out.println("Enter your Password:");
        final String password = SCANNER.next();

        User loggingInUser = UserRepository.getSpecificUser(userId);

        if (loggingInUser != null && loggingInUser.getUserId().equals(userId)
                && loggingInUser.getPassword().equals(Utility.hashPassword(password))) {
            System.out.println("User Logged In!");
        } else {
            System.err.println("Invalid UserId or Password!");
            loggingInUser = null;
        }

        return loggingInUser;
    }
}
