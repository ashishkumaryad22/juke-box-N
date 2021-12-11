package org.example.hellomaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection con;

    public static Connection getConnection() throws SQLException {
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox","root", "password@123");
        return con;
    }
}
