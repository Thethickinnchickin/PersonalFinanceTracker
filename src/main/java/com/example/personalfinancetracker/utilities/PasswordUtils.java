package com.example.personalfinancetracker.utilities;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class PasswordUtils {

    private static final int SALT_LENGTH = 16; // 16 bytes (128 bits)
    private static final int HASH_LENGTH = 64; // 64 bytes
    private static final int ITERATIONS = 10000; // Recommended iteration count

    // Hashes the password with a random salt using PBKDF2
    public static String hashPassword(String password) {
        try {
            // Generate a salt
            byte[] salt = new byte[SALT_LENGTH];
            SecureRandom.getInstance("SHA1PRNG").nextBytes(salt);

            // Hash the password with the salt using PBKDF2
            byte[] hashedPassword = hash(password, salt);

            // Store salt and hashed password
            String saltString = Base64.getEncoder().encodeToString(salt);
            String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);

            return saltString + ":" + hashedPasswordString; // Return salt and hash concatenated
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Hash the password using PBKDF2WithHmacSHA256
    private static byte[] hash(String password, byte[] salt) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec key = new SecretKeySpec(salt, "HmacSHA256");
        mac.init(key);

        byte[] hash = password.getBytes();
        for (int i = 0; i < ITERATIONS; i++) {
            hash = mac.doFinal(hash);
        }

        return hash;
    }

    // Verifies that the given password matches the stored hash
    public static boolean verifyPassword(String password, String storedPassword) {
        try {
            // Split the stored password into username, hash, and salt
            String[] parts = storedPassword.split(":");
            if (parts.length != 3) {
                throw new RuntimeException("Invalid stored password format");
            }

            String username = parts[0];           // This is the username
            String storedHashString = parts[1];   // This is the hashed password
            String saltString = parts[2];         // This is the salt used for hashing

            // Convert from Base64
            byte[] salt = Base64.getDecoder().decode(saltString);
            byte[] storedHash = Base64.getDecoder().decode(storedHashString);

            // Hash the password entered by the user with the stored salt
            byte[] userHash = hash(password, salt);

            // Compare the stored hash with the newly generated hash
            return java.util.Arrays.equals(storedHash, userHash);
        } catch (Exception e) {
            throw new RuntimeException("Error verifying password", e);
        }
    }


}
