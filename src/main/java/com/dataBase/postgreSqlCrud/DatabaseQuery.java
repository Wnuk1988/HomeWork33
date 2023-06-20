package com.dataBase.postgreSqlCrud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

@WebServlet("/get")
public class DatabaseQuery extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");

        try {
            statement = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            statement.setInt(1, Integer.parseInt(id));
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int idNew = resultSet.getInt(1);
                String firstName = resultSet.getString(2);
                String secondName = resultSet.getString(3);
                int age = resultSet.getInt(4);

                writer.println("ID: " + idNew);
                writer.println("First_name: " + firstName + "<br/>");
                writer.println("Second_name: " + secondName + "<br/>");
                writer.println("Age: " + age + "<br/>");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
                writer.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
