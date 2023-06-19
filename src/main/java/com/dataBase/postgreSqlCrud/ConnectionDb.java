package com.dataBase.postgreSqlCrud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDb {
    public static Connection connectDb(String nameDb, String user, String password) {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/";
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url + nameDb, user, password);
            if (connection != null) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Connection failed!");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
