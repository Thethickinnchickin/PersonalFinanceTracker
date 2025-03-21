package com.example.personalfinancetracker.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String URL = "jdbc:sqlite:database/finance.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
        return conn;
    }

    public static void createTables() {
        String transactionsTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type TEXT NOT NULL," +
                "amount REAL NOT NULL," +
                "category TEXT NOT NULL," +
                "date TEXT NOT NULL)";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(transactionsTable);
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}
