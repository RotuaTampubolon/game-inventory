package com.gameinventory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL  = "jdbc:postgresql://localhost:5432/game_inventory";
    private static final String USER = "postgres"; //username PostegreSQL kalian buat yaa
    private static final String PASS = "password"; //password PostegreSQL kalian buat yaa, dan hapus komen ini 

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