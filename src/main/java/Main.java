import Authorization.AuthManager;
import controller.FilmController;
import data.PostgreDB;
import repositories.FilmDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Connection connection = null ;

        FilmDao filmDao = new FilmDao(connection);
        FilmController filmController = new FilmController(filmDao);

        try {
            connection = PostgreDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Welcome!");
        System.out.println("Choose your option");
        System.out.println("1. Sign in.");
        System.out.println("2. Sign up.");
        System.out.println("Enter number:");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                AuthManager.signIn(scanner);
                break;
            case 2:
                AuthManager.signUp(scanner, connection, filmController);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }
}