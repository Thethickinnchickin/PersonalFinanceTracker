package com.example.personalfinancetracker.controllers;

import javafx.fxml.FXML;

public class MainPageController {
    @FXML
    private void handleGoToDashboard() {
        System.out.println("Navigating to Dashboard...");
        // Add navigation logic here (e.g., load a new scene or show a new view)
    }

    @FXML
    private void handleAccountSettings() {
        System.out.println("Navigating to Account Settings...");
        // Add navigation logic here
    }

    @FXML
    private void handleViewTransactions() {
        System.out.println("Viewing Transactions...");
        // Add navigation logic here
    }
}
