package com.dataBase.postgreSqlCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

public class DeleteRow {
    public static void main(String[] args) {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setInt(1, 1);
            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
