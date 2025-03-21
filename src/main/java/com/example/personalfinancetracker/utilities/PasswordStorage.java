package com.example.personalfinancetracker.utilities;

import java.io.*;
import java.util.*;

public class PasswordStorage {

    private static final String FILE_NAME = "E:/user_passwords.txt"; // File to store passwords

    // Store the username and the hashed password in a local file
    public static void storePassword(String username, String hashedPassword, String salt) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Save in format: username:hashedPassword:salt
            writer.write(username + ":" + hashedPassword + ":" + salt);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Retrieve the full username:hashedPassword:salt string for a given username
    public static String getPasswordHashForUsername(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts[0].equals(username)) {
                    return line;  // Return the full line: username:hashedPassword:salt
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // No user found
    }
}
