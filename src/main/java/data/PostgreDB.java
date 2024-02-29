package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDB {

    // Username and password, connection to database
    static String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
    static String username = "postgres";
    static String password = "0000";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(connectionString, username, password);
    }
}
