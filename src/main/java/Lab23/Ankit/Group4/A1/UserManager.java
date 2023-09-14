package Lab23.Ankit.Group4.A1;

import java.io.*;
import java.util.*;

public class UserManager {
    private static final String USER_DATABASE_PATH = "user_database.txt";

    // User login function
    public static boolean login(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATABASE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Login failed: " + e.getMessage());
        }
        return false;
    }

    // User registration function
    public static void register(String username, String password) {
        // Check if the username starts with a letter
        if (!username.matches("^[A-Za-z].*")) {
            System.out.println("Username must start with a letter.");
            return; // Stop the registration process
        }

        // Check if the password contains at least 8 characters
        if (password.length() < 8) {
            System.out.println("Password must contain at least 8 characters.");
            return; // Stop the registration process
        }

        // Check if the username and password contain spaces
        if (username.contains(" ") || password.contains(" ")) {
            System.out.println("Username and password cannot contain spaces.");
            return; // Stop the registration process
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user_database.txt", true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
