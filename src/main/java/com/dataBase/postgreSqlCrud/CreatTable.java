package com.dataBase.postgreSqlCrud;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static com.dataBase.postgreSqlCrud.ConnectionDb.connectDb;

public class CreatTable {
    public static void main(String[] args) {
        Connection connection = connectDb("HomeWork33", "postgres", "root");
        Statement statement = null;
        try {
            String query = new StringBuilder().append("create table users ").append(" (id bigserial primary key,")
                    .append("first_name varchar(20) default 'first_name'::character varying,")
                    .append("second_name varchar(20) default 'second_name'::character varying,")
                    .append("age integer not null);").toString();
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table has been created successfully!");
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

