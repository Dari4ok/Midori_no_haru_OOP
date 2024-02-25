package data;

import data.Interface.DBConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreDB  implements DBConnection {

    // Username and password, connection to database
    String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
    String username = "postgres";
    String password = "0000";

    @Override
    public Connection getConnection() throws SQLException, ClassNotFoundException {

        try {
            Class.forName("org.postgresql.Driver");

            Connection con = DriverManager.getConnection(connectionString, username, password);

            return con;
        }catch (Exception e){
            System.out.println("Connection error!" + e.getMessage());
            return null;
        }
    }
}
