package com.example.personalfinancetracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;  // Change to GridPane
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/personalfinancetracker/views/login.fxml"));

        // Use GridPane instead of StackPane
        GridPane root = loader.load();

        // Set the scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Personal Finance Tracker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
