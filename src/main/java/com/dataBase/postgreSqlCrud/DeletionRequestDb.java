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

@WebServlet("/delete")
public class DeletionRequestDb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String id = req.getParameter("id");
        try {
            statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
            statement.setInt(1, Integer.parseInt(id));
            int result = statement.executeUpdate();
            writer.println("Пользователь с этой id: " + id + " успешно удален, количество: " + result);
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
