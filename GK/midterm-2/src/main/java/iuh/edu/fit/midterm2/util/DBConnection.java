package iuh.edu.fit.midterm2.util;

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
    private static final String URL="jdbc:mariadb://localhost:3306/quanlytintuc";
    private static final String USER="root";
    private static final String PASSWORD="123456";

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
