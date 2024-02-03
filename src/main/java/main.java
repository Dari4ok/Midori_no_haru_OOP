import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {
   public void Main(String[] args){
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(connectionString, "postgres", "0000");
        } catch (SQLException e){
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("driver not fond: " + e.getMessage());
        }
   }
}
