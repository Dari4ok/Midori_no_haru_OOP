import database.FilmDao;
import general.Film;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static database.FilmDao.exitProgram;
import static general.DisplayFilms.displayFilms;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = null;

        // Username and password, connection to database
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        String username = "postgres";
        String password = "0000";

        FilmDao filmDao = new FilmDao (connectionString, username, password);

        boolean exit = false;

        // This is infinite while loop for console (CRUD)
        System.out.println("Welcome user! \n Please select an option from menu:");
        while (true) {
            System.out.println("1. View all films");
            System.out.println("2. Add film");
            System.out.println("3. Delete film");
            System.out.println("4. Setting new id");
            System.out.println("0. Exit");
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
