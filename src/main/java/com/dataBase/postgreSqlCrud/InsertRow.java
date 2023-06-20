package com.dataBase.postgreSqlCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

public class InsertRow {
    public static void main(String[] args) {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO users (id,first_name,second_name,age) values (DEFAULT,'Mikhail','Unuchko',35), " +
                    "(DEFAULT,'Natalia','Unuchko',33), (DEFAULT,'Sofia','Unuchko',7), (DEFAULT,'Sergei','Gardel',34), " +
                    "(DEFAULT,'Oleg','Gardel',61), (DEFAULT,'Nadia','Purshadan',37)");
            statement.executeUpdate();
            System.out.println("Inserted successfully!");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
