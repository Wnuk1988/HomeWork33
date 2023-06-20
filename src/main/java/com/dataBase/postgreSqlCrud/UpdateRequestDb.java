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
import java.sql.SQLException;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

@WebServlet("/change-secondName")
public class UpdateRequestDb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");
        String secondName = req.getParameter("secondName");
        try {
            statement = connection.prepareStatement("UPDATE users SET second_name=? WHERE id=?");
            statement.setString(1, secondName);
            statement.setInt(2, Integer.parseInt(id));
            statement.executeUpdate();
            writer.println("У пользователя с id: " + id + " успешно изменена фамилия на: " + secondName);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                connection.close();
                statement.close();
                writer.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
