package com.uduran.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Europe/Madrid";
    private static String username = "root";
    private static String password = "gilro";

    public static Connection getInstance() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }


}