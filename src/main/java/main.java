import Authorization.AuthManager;
import controller.FilmController;
import repositories.FilmDao;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        /*// Username and password, connection to database
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        String username = "postgres";
        String password = "0000";

        //FilmDao filmDao = new FilmDao(connectionString, username, password);
        //FilmController controller = new FilmController(filmDao);
        //MyApplication application = new MyApplication(controller);

        //application.start();*/

        System.out.println("[+] Welcome!");
        System.out.println("[+] Choose your option:");
        System.out.println("[1] Sign in.");
        System.out.println("[2] Sign up.");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                AuthManager.signIn(scanner);
                break;
            case 2:
                AuthManager.signUp(scanner);
                break;
        }
    }
}
