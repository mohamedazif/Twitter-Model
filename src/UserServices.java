import java.util.Map;
import java.util.Scanner;


/**
 * UserServices is a class which acts as a utility class for user services like register,
 * login, view profile and show follow suggestions
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public final class UserServices {

    private static final Scanner SCANNER = new Scanner(System.in);

    private  UserServices(){}

    /**
     * To register users with email format checking and password format checking
     */
    static void registerUser() {
        System.out.println("Enter the following details:" + "\nEnter Name:");
        String userName = SCANNER.next();
        System.out.println("Enter Email-Id:");
        String email = SCANNER.next();

        if (!isValidEmail(email)) {
            System.err.println("Invalid Email\nUser not registered");
            return;
        }

        System.out.println("Create User-ID:");
        String userId = SCANNER.next();
        System.out.println("Create Password:");
        String password = SCANNER.next();

        if (!isValidPassword(password)) {
            System.err.println("Password must contain an Uppercase, a lowercase " +
                    "and a digits and with minimum 8 characters");
            return;
        }

        System.out.println("Enter your age:");
        int age = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.println("Tell about yourself:");
        String bio = SCANNER.nextLine();

        User user = new User();

        if ((user.setUser(userName, userId, email, password, bio, age))) {
            System.out.println("User Registered!");
        } else {
            System.err.println("User not registered!");
        }
    }

    /**
     * To check the email format
     * @param email Email produced by the user
     * @return true if format matches else false
     */
    private static boolean isValidEmail(String email) {
        final String emailRegex = "^[A-Za-z0-9+.-_]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }

    /**
     * To check password format
     * @param password Password created by the user
     * @return true if format matches else false
     */
    private static boolean isValidPassword(String password) {
        final String pwdRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$";
        return password.matches(pwdRegex);
    }

    /**
     * To check login of the users
     * @return User object if produced UserId and Password matches the
     *          registered User's UserId and Password else null
     */
     static User loginUser() {
        System.out.println("Enter your User-Id:");
        String userId = SCANNER.next();
        System.out.println("Enter your Password:");
        String password = SCANNER.next();
        User loggingInUser = User.getSpecificUser(userId);

        if (loggingInUser.getUserId().equals(userId) && loggingInUser.getPassword()
                .equals(password)) {
            System.out.println("User Logged In!");
        } else {
            System.err.println("User not found!");
            loggingInUser = null;
        }

        return loggingInUser;
    }

    /**
     * To show the Profile of the logged-in User
     * @param user logged-in User object
     */
    public static void showProfile(User user) {
        System.out.println("UserId - " + user.getUserId()
                            + "\nWelcome " + user.getUserName() + " to the Twitter World\n"
                            + user.getBio() + "\nFollowing: " + user.getFollowing().toString()
                            + "\nFollowers:" + user.getFollowers() + "\nMy Tweets:");

        for (Tweet tweet : Tweet.getSpecificUserTweets(user.getUserId())) {
            System.out.println(tweet.getUserId() + "\n" + tweet.getTweetContent()
                                + "\nLikes: " + tweet.getLikesCount() + "\tRetweets: "
                                + tweet.getRetweetCount());
        }
        SCANNER.nextLine();
        SCANNER.nextLine();
    }

    /**
     * To suggest the logged-in User to follow some friends
     * @param user logged-in User object
     */
    public static void showUsersToFollow(User user) {
        System.out.println("Follow Suggestions for " + user.getUserName());
        Map<String, User> usersList = User.getUsersList();

        for (User suggUser : usersList.values()) {
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
                }
            }
        }
    }

    public static void postTweet(String userID) {
        System.out.println("Write your tweet thoughts:");
        SCANNER.nextLine();
        String tweetPost = SCANNER.nextLine();
        Tweet tweet = new Tweet(userID, tweetPost);
        System.out.println("Tweet Posted! on " + tweet.getUserId());
    }

    public static void showTimeLine(User user) {
        System.out.println("Timeline of " + user.getUserId());

        for (Tweet tweet : Tweet.getTimelineTweets(user)) {
            System.out.println(tweet.getUserId() + "\n" + tweet.getTweetContent()
                    + "\nLikes: " + tweet.getLikesCount() + "\tRetweets: "
                    + tweet.getRetweetCount());
        }
    }
}