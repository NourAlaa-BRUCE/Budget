// This class represents a user with a username, email, phone number, and password.
// It provides methods to retrieve the user's details.

package src.models;

public class User {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;

    public User(String username, String email, String phoneNumber, String password) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getUsername()    { return username; }
    public String getEmail()       { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getPassword()    { return password; }
}
