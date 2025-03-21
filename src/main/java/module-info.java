module com.example.personalfinancetracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;

    opens com.example.personalfinancetracker to javafx.fxml;
    opens com.example.personalfinancetracker.controllers to javafx.fxml;
    exports com.example.personalfinancetracker;
}
