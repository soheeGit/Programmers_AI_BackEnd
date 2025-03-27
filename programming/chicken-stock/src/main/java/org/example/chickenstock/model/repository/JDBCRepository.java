package org.example.chickenstock.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface JDBCRepository {
    default Connection getConnection(String url, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }
}
