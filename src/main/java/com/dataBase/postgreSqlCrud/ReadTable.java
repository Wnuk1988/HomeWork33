package com.dataBase.postgreSqlCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

public class ReadTable {
    public static void main(String[] args) {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();
            System.out.println("id\tfirst_name\tsecond_name\t\tage");
            System.out.println("-------------------------------------");
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t\t");
                System.out.print(resultSet.getString("second_name") + "\t\t\t");
                System.out.println(resultSet.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
