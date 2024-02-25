import data.PostgreDB;
import repositories.FilmDao;
import models.Film;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static repositories.FilmDao.exitProgram;
import static console.DisplayFilms.displayFilms;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PostgreDB postgreDB = new PostgreDB();

        // Username and password, connection to database
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        String username = "postgres";
        String password = "0000";

        FilmDao filmDao = new FilmDao (connectionString, username, password);

        Connection connection = null;

        try {
            postgreDB.getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        // This is infinite while loop for console (CRUD)
        boolean exit = false;

        System.out.println("******************************************************");
        System.out.println("Welcome user! \n -------------------------------------" +
                " \nPlease select an option from menu:");
        while (true) {
            System.out.println("\n1. View all films");
            System.out.println("\n2. Add film");
            System.out.println("\n3. Delete film");
            System.out.println("\n4. Setting new id");
            System.out.println("\n0. Exit");
            System.out.println("******************************************************");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice){

                case 1: // Shows all films in the database
                    List<Film> users = filmDao.getAllFilms();
                    displayFilms(users);
                    break;

                case 2: // In this case we're adding new film to database
                    scanner.nextLine(); // Consume the newline character

                    String filmName;
                    do {
                        System.out.print("Enter film name: ");
                        filmName = scanner.nextLine().trim();

                        if (filmName.isEmpty()) {
                            System.out.println("Film name cannot be empty. Please try again.");
                        }
                    } while (filmName.isEmpty());

                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();

                    System.out.print("Enter genres: ");
                    String genres = scanner.nextLine();

                    System.out.println("Status: ");
                    System.out.println("1. Completed");
                    System.out.println("2. Plan to Watch");
                    System.out.println("3. On Hold");
                    int myListStatus = scanner.nextInt();

                    String myList = "";
                    switch (myListStatus) {
                        case 1:
                            myList = "Completed";
                            break;
                        case 2:
                            myList = "Plan to Watch";
                            break;
                        case 3:
                            myList = "On Hold";
                            break;
                        default:
                            System.out.println("Invalid status. Defaulting to Plan to Watch.");
                            myList = "Plan to Watch";
                    }

                    Film newFilm = new Film(0, filmName, director, genres, myList);
                    filmDao.addFilm(newFilm);
                    System.out.println("Film added successfully!");
                    break;

                case 3:
                    // Here is film deleting
                    System.out.print("Enter the ID of the film you want to delete: ");
                    int filmIdToDelete = scanner.nextInt();

                    Film filmToDelete = filmDao.getFilmById(filmIdToDelete);
                    if (filmToDelete != null) {
                        filmDao.deleteFilm(filmIdToDelete);
                        System.out.println("Film with ID " + filmIdToDelete + " deleted successfully.");
                    } else {
                        System.out.println("Film with ID " + filmIdToDelete + " not found in the database.");
                    }
                    break;

                case 4:
                    // Setting new ID, it's important to set ID after deleting film from table. Just for decreasing back
                    String sequenceName = "users_id_seq";
                    System.out.print("Enter a new Id: ");
                    int restartValue = scanner.nextInt(); // Set the restart value to the desired ID
                    filmDao.resetSequence(sequenceName, restartValue);
                    break;

                case 0:
                    // Exiting program
                    exitProgram();
                    exit = true;
                    System.out.println("Good bye!");
                    break;
                default:
                    System.out.println("Incorrect choice!");

            }
        }
    }
}
