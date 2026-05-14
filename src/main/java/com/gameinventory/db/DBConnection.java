package com.gameinventory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL  = "jdbc:postgresql://localhost:5432/game_inventory";
    private static final String USER = "postgres"; 
    private static final String PASS = "0000"; 

    private DBConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Database connected successfully.");
        } catch (SQLException e) {
            throw new RuntimeException("❌ Failed to connect to database: " + e.getMessage());
        }
    }

    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}