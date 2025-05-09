package src.services;

import src.models.User;
import src.storage.UserStorage;
import src.utils.Validator;

import java.util.Scanner;

public class AuthService {
    private final Scanner sc = new Scanner(System.in);
    private User currentUser = null;

    public void signUp() {
        System.out.println("\n--- Sign Up ---");
        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();
        if (!Validator.isValidEmail(email)) {
            System.out.println("Invalid email format."); return;
        }
        if (UserStorage.exists(email)) {
            System.out.println("Email already registered."); return;
        }

        System.out.print("Phone (+20xxxxxxxxxx): ");
        String phone = sc.nextLine();
        if (!Validator.isValidPhoneNumber(phone)) {
            System.out.println("Invalid phone number."); return;
        }

        System.out.print("Password: ");
        String password = sc.nextLine();
        if (!Validator.isValidPassword(password)) {
            System.out.println("Password must be 8-16 chars, include upper, lower, digit, special."); return;
        }

        System.out.print("Confirm Password: ");
        String confirm = sc.nextLine();
        if (!password.equals(confirm)) {
            System.out.println("Passwords do not match."); return;
        }

        System.out.println("Sending OTP... (Your code is 1234)");
        System.out.print("Enter OTP: ");
        String otp = sc.nextLine();
        if (!"1234".equals(otp)) {
            System.out.println("Invalid OTP."); return;
        }

        User newUser = new User(username, email, phone, password);
        UserStorage.addUser(newUser);
        currentUser = newUser;
        System.out.println("Account created! Logged in as " + username + ".");
    }

    public boolean login() {
        System.out.println("\n--- Log In ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass  = sc.nextLine();

        User user = UserStorage.getUserByEmail(email);
        if (user == null) {
            System.out.println("User not found."); return false;
        }
        if (!user.getPassword().equals(pass)) {
            System.out.println("Incorrect password."); return false;
        }
        currentUser = user;
        System.out.println("Welcome back, " + user.getUsername() + "!");
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}