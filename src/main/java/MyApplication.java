import controller.FilmController;
import models.Film;

import java.util.Scanner;

public class MyApplication {
    private final FilmController controller;
    private final Scanner scanner;

    public MyApplication(FilmController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;

        System.out.println("******************************************************");
        System.out.println("""
                Welcome user!\s
                 -------------------------------------\s
                Please select an option from menu:""");
        while (!exit) {
            System.out.println("\n1. View all films");
            System.out.println("\n2. Add film");
            System.out.println("\n3. Delete film");
            System.out.println("\n4. Setting new id");
            System.out.println("\n0. Exit");
            System.out.println("******************************************************");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(controller.getAllFilms());
                    break;
                case 2:
                    addFilm();
                    break;
                case 3:
                    deleteFilm();
                    break;
                case 4:
                    resetSequence();
                    break;
                case 0:
                    exitProgram();
                    exit = true;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Incorrect choice!");
            }
        }
    }

    private void addFilm() {
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
        scanner.nextLine(); // Consume the newline character

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
        Film film = new Film(0,filmName, director, genres, myList);
        controller.addFilm(film);
    }



    private void deleteFilm() {
        System.out.print("Enter the ID of the film you want to delete: ");
        int filmIdToDelete = scanner.nextInt();

        System.out.println(controller.deleteFilm(filmIdToDelete));
    }

    private void resetSequence() {
        String sequenceName = "users_id_seq";
        System.out.print("Enter a new Id: ");
        int restartValue = scanner.nextInt();
        controller.resetSequence(sequenceName, restartValue);
    }

    private void exitProgram() {
        System.exit(0);
    }
}
