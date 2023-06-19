package com.dataBase.postgreSqlCrud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

public class UpdateRow {
    public static void main(String[] args) {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE users SET second_name=?, age=? WHERE id=?");
            statement.setString(1, "Koshal");
            statement.setInt(2, 24);
            statement.setInt(3, 6);
            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
