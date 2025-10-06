import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User class for initializing user and retrieving users
 *
 * @version                     1.0 25 Sept 2025
 * @author                      Mohamed Azif
 */
public class User {

    private static final Collection<String> userIdList = new ArrayList<>();
    private static final Collection<String> emailList = new ArrayList<>();
    private static final Map<String, User> userList = new HashMap<>();

    private String userName;
    private String userId;
    private String email;
    private String password;
    private String bio;
    private int age;
    private final Collection<String> followers = new ArrayList<>();
    private final Collection<String> following = new ArrayList<>();

    /**
     * setUser method is used to initialize the users and store their information
     * @param userName Name of the user
     * @param userId Unique ID created by the user
     * @param email Mail-ID of the user
     * @param password Password created by the user
     * @param bio About the user by him/her self
     * @param age Age of the user
     * @return true if the user is created successfully else false
     */
    public final boolean setUser(final String userName, final String userId, final String email,
                                 final String password, final String bio, final int age) {
        /* Checks whether the userId/emailId already exits */
        if (userIdList.contains(userId)) {
            System.err.println("UserId is taken choose another!");
            return false;
        } else if (emailList.contains(email)){
            System.err.println("Email-Id registered already!");
            return false;
        } else {
            this.userName = userName;
            this.userId = userId;
            this.email = email;
            this.password = password;
            this.bio = bio;
            this.age = age;
            userIdList.add(userId);
            emailList.add(email);
            userList.put(userId, this);
            return true;
        }
    }

    public String getUserName(){
        return userName;
    }

    public String getUserId(){
        return userId;
    }

    public String getPassword(){
        return password;
    }

    public String getBio(){
        return bio;
    }

    public Collection<String> getFollowers(){
        return followers;
    }

    public Collection<String> getFollowing(){
        return following;
    }

    /**
     * Static method to return all the registered Users
     * @return Returns a hashmap of Users where UserID is mapped to User Object
     */
    public static Map<String, User> getUsersList(){
        return userList;
    }

    /**
     * Static method to get a User who has the specific UserID
     * @param userId To retrieve the User with this UserID
     * @return Returns User Object if UserID is found else null
     */
    public static User getSpecificUser(String userId){
        return userList.get(userId);
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }
}