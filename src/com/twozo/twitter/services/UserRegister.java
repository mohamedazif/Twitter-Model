package com.twozo.twitter.services;

import com.twozo.twitter.entity.User;
import com.twozo.twitter.repository.UserRepository;
import com.twozo.twitter.services.interfaces.RegisterService;

import java.util.Scanner;

public final class UserRegister implements RegisterService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * To Register the user in our app.
     */
    public void registerUser() {
        System.out.println("Enter the following details:" + "\nEnter Name:");
        String userName = SCANNER.nextLine();
        System.out.println("Enter Email-Id:");
        String email = SCANNER.next();

        if (!Utility.isValidEmail(email)) {
            System.err.println("Invalid Email\nUser not registered");
            return;
        }

        System.out.println("Create User-ID:");
        String userId = SCANNER.next();
        System.out.println("Create Password:");
        String password = SCANNER.next();

        if (!Utility.isValidPassword(password)) {
            System.err.println("Password must contain an Uppercase, a "
                    + "lowercase and a digits and with minimum 8 characters");
            return;
        }

        System.out.println("Enter your age:");
        int age = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Tell about yourself:");
        String bio = SCANNER.nextLine();

        User user = new User(userName, userId, email,
                Utility.hashPassword(password), age, bio);

        if (UserRepository.addRegisteredUser(userId, user)) {
            System.out.println("User Registered successfully!");
        } else {
            System.err.println("User not registered!");
        }
    }
}
