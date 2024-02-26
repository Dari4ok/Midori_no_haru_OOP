import controller.FilmController;
import repositories.FilmDao;
import java.sql.SQLException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // Username and password, connection to database
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        String username = "postgres";
        String password = "0000";

        FilmDao filmDao = new FilmDao(connectionString, username, password);
        FilmController controller = new FilmController(filmDao);
        Scanner scanner = new Scanner(System.in);
        MyApplication application = new MyApplication(controller);

        application.start();
    }
}
