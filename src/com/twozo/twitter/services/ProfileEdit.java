package com.twozo.twitter.services;

import com.twozo.twitter.entity.User;
import com.twozo.twitter.services.interfaces.EditService;

import java.util.Scanner;

public class ProfileEdit implements EditService {

    private static final Scanner SCANNER = new Scanner(System.in);

    /**
     * For editing the details of the user.
     * @param user Logged-in user
     */
    public void editProfile(final User user) {
        System.out.println("""
                Edit Profile:
                1. Change Username
                2. Change Password
                3. Change Age
                4. Change Bio""");
        int choice = SCANNER.nextInt();

        switch (choice) {
            case 1 -> editUsername(user);
            case 2 -> editPassword(user);
            case 3 -> editAge(user);
            case 4 -> editBio(user);
            default -> System.out.println("Invalid option!");
        }
    }

    /**
     * To edit bio of the user.
     * @param user Logged-in user
     */
    private void editBio(final User user) {
        System.out.println("Enter a new bio: ");
        user.setBio(SCANNER.nextLine());
    }

    /**
     * To edit age of the user.
     * @param user Logged-in user
     */
    private void editAge(final User user) {
        System.out.println("Your current age: " + user.getAge()
                + "\nEnter your age:");
        user.setAge(SCANNER.nextInt());
    }

    /**
     * To change password of th user.
     * @param user Logged-in user
     */
    private void editPassword(final User user) {
        System.out.println("Enter your new password:");
        String password = SCANNER.next();
        if (Utility.isValidPassword(password)) {
            user.setPassword(Utility.hashPassword(password));
            System.out.println("Password changed successfully.");
        } else {
            System.err.println("Invalid Password!");
        }
    }

    /**
     * To change username of the user.
     * @param user Logged-in user.
     */
    private void editUsername(final User user) {
        System.out.println("Enter your new Username:");
        user.setUserName(SCANNER.nextLine());
    }
}
