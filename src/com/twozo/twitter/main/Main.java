package com.twozo.twitter.main;

/*
 * Making a Twitter based application in Console.
 *
 * version 1.0
 */

import com.twozo.twitter.entity.User;
import com.twozo.twitter.services.*;
import com.twozo.twitter.services.interfaces.*;

import java.util.Scanner;

/**
 * Main class for a simple Twitter model console application.
 *
 * @version             1.0 25 Sept 2025
 * @author              Mohamed Azif
 */
public final class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static User loggedUser = null;

    private Main() { }

    /**
     * Main method.
     * @param args Command line arguments
     */
    public static void main(final String[] args) {
        System.out.println("Hello and welcome to Twitter model!");
        int choice;

        do {
            showMenu();
            choice = getChoice();
            handleChoice(choice);
        } while (choice != 0);

        System.out.println("Goodbye for now!");
//        System.exit(0);
    }

    /**
     * Displays the menu options depending on user login state.
     */
    private static void showMenu() {
        SCANNER.nextLine();
        if (loggedUser == null) {
            System.out.println("""
                    Menu:
                    1. Register
                    2. Login""");
        } else {
            System.out.println("""
                    Menu:
                    1. Timeline
                    2. Profile
                    3. Post Tweet
                    4. Follow Suggestions
                    5. Logout""");
        }

        System.out.println("0. Exit");
    }

    /**
     * Reads the user's choice and ensures it is a valid integer.
     *
     * @return the user's numeric choice
     */
    private static int getChoice() {
        System.out.print("Enter your choice: ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Please enter a number:");
            SCANNER.next();
        }

        int choice = SCANNER.nextInt();
        SCANNER.nextLine();
        return choice;
    }

    /**
     * Handles the menu choice depending on whether the user is logged in.
     *
     * @param choice the menu choice entered by the user
     */
    private static void handleChoice(final int choice) {

        if (loggedUser == null) {
            RegisterService registerService = ServiceFactory.getRegisterService();
            LoginService loginService = ServiceFactory.getLoginService();

            switch (choice) {
                case 1 -> registerService.registerUser();
                case 2 -> loggedUser = loginService.loginUser();
                case 0 -> { }
                default -> System.out.println("Invalid option!");
            }
        } else {
            TimelineService timelineService = ServiceFactory.getTimelineService();
            ProfileService profileService = ServiceFactory.getProfileService();
            TweetService tweetService = ServiceFactory.getTweetService();
            FollowSuggestionService followSuggestionService = ServiceFactory.
                    getFollowSuggestionService();

            switch (choice) {
                case 1 -> timelineService.showTimeLine(loggedUser);
                case 2 -> profileService.showProfile(loggedUser);
                case 3 -> tweetService.postTweet(loggedUser.getUserId());
                case 4 -> followSuggestionService.suggestFollowers(loggedUser);
                case 5 -> {
                    loggedUser = null;
                    System.out.println("User logged out!");
                }
                case 0 -> { }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}