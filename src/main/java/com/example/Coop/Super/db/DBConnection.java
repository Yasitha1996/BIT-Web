package com.example.Coop.Super.db;

import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnection {

    private static DBConnection instance;
    private static Connection con;

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_db?auroReconnect=true&useSSL=false", "root", "1234");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("DB Exception: " + e);
        }
    }

    public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public static Connection getConnection() {
        return con;
    }

    public static void close(AutoCloseable closeable) throws Exception {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
