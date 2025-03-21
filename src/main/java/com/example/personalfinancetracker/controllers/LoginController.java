package com.example.personalfinancetracker.controllers;

import com.example.personalfinancetracker.utilities.PasswordStorage;
import com.example.personalfinancetracker.utilities.PasswordUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    // Method called when the Login button is pressed
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Retrieve the full stored password data for the username
        String storedPasswordHash = PasswordStorage.getPasswordHashForUsername(username);

        // If username is valid and password matches
        if (storedPasswordHash != null && PasswordUtils.verifyPassword(password, storedPasswordHash)) {
            System.out.println("Login successful!");
            showMainPage();  // Redirect to the main page
        } else {
            System.out.println("Invalid credentials.");
            showErrorMessage();  // Show error message
        }
    }

    @FXML
    private void handleRegistration() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Hash the password before storing it
        String hashedPasswordAndSalt = PasswordUtils.hashPassword(password);

        // Split the returned string into the hashed password and salt
        String[] parts = hashedPasswordAndSalt.split(":");
        String hashedPassword = parts[1];  // The hashed password
        String salt = parts[0];            // The salt

        // Store the username, hashed password, and salt locally
        PasswordStorage.storePassword(username, hashedPassword, salt);

        System.out.println("User registered successfully!");
    }


    // Show an error message if login fails
    private void showErrorMessage() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("Invalid Credentials");
        alert.setContentText("The username or password you entered is incorrect.");
        alert.showAndWait();
    }

    // Show the main page after a successful login
    private void showMainPage() {
        try {
            // Load the main page (replace with actual MainPage.fxml path)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personalfinancetracker/views/MainPage.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show the main page after a successful login
    public void showRegistrationPage() {
        try {
            // Load the main page (replace with actual MainPage.fxml path)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personalfinancetracker/views/register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) usernameField.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
