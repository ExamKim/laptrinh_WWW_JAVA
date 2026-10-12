package com.example.midterm3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author EXAM
 * @version 1.0
 * @MSSV 23633041
 * @Class DHKTPM19ATT
 * @since 10/12/2026
 */
public class DBConnection {
    private final static String URL="jdbc:mariadb://localhost:3306/companydb";
    private final static String USER="root";
    private final static String PASSWORD="123456";

    public DBConnection() {
    }
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(URL,USER,PASSWORD);
    }

}
