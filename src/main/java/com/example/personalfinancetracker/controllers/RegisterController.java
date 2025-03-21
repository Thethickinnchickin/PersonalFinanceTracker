package com.example.personalfinancetracker.controllers;

import com.example.personalfinancetracker.utilities.PasswordStorage;
import com.example.personalfinancetracker.utilities.PasswordUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
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
        try {
            PasswordStorage.storePassword(username, hashedPassword, salt);

            System.out.println("User registered successfully!");
            showLoginPage();
        } catch (Exception e) {
            showErrorMessage();
        }

    }

    // Show an error message if login fails
    private void showErrorMessage() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Registration Error");
        alert.setHeaderText("Something went wrong on Registration");
        alert.setContentText("The username or password you entered is incorrect.");
        alert.showAndWait();
    }

    // Show the main page after a successful login
    public void showLoginPage() {
        try {
            // Load the main page (replace with actual MainPage.fxml path)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personalfinancetracker/views/login.fxml"));
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
