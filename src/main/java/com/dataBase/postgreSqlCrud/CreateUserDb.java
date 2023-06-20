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

@WebServlet("/create")
public class CreateUserDb extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/forma.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        PreparedStatement statement = null;
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        String firstName = req.getParameter("userFirstName");
        String secondName = req.getParameter("userSecondName");
        String age = req.getParameter("userAge");
        try {
            statement = connection.prepareStatement("INSERT INTO users (id,first_name,second_name,age)" +
                    " values (DEFAULT,?,?,?)");
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setInt(3, Integer.parseInt(age));
            int result = statement.executeUpdate();
            writer.println("Добавили " + result + "пользователя с информацией из формы!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
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