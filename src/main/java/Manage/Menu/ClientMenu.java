package Manage.Menu;

import controller.FilmController;
import models.Film;
import repositories.interfaces.FilmInterface;

import java.util.List;
import java.util.Scanner;

public class ClientMenu {
    FilmController controller;
    public void launchMenu(FilmInterface filmInterface) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Client Menu:");
            System.out.println("1. View all films");
            System.out.println("2. Add film");
            System.out.println("3. Update Status");
            System.out.println("4. Sign out.");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllFilms(filmInterface);
                    break;
                case 2:
                    addFilm(filmInterface, scanner);
                    break;
                case 3:
                    updateFilmStatus(filmInterface, scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showAllFilms(FilmInterface filmInterface) {
        List<Film> films = filmInterface.getAllFilms();
        System.out.println("Films: ");
        for (Film film: films) {
            System.out.println(((Film) film).toString());
        }
        System.out.println();
    }

    private void addFilm(FilmInterface filmInterface,Scanner scanner) {
        System.out.println("Adding a new film!");
        System.out.print("Enter film name:");
        String filmName;
        do {
            System.out.print("Enter film name: ");
            filmName = scanner.nextLine().trim();

            if (filmName.isEmpty()) {
                System.out.println("Film name cannot be empty. Please try again.");
            }
        } while (filmName.isEmpty());
        System.out.println("Enter director:");
        String director = scanner.nextLine();
        System.out.print("Enter genres: ");
        String genres = scanner.nextLine();
        System.out.println("Status: ");
        System.out.println("1. Completed");
        System.out.println("2. Plan to Watch");
        System.out.println("3. On Hold");
        int myListStatus = scanner.nextInt();
        scanner.nextLine();

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
        Film newfilm = new Film(0,filmName, director, genres, myList);
        controller.addFilm(newfilm);
    }

    private void updateFilmStatus(FilmInterface filmInterface, Scanner scanner) {
        System.out.println("Updating film status!");
        System.out.println("Enter the ID of the film you want to update status for:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character left by nextInt()

        System.out.println("Enter the new status:");
        System.out.println("1. Completed");
        System.out.println("2. Plan to Watch");
        System.out.println("3. On Hold");
        int statusChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character left by nextInt()

        String newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = "Completed";
                break;
            case 2:
                newStatus = "Plan to Watch";
                break;
            case 3:
                newStatus = "On Hold";
                break;
            default:
                System.out.println("Invalid status. No changes will be made.");
                return;
        }
        if (filmInterface.updateStatus(id, newStatus)) {
            System.out.println("Film status updated successfully!");
        } else {
            System.out.println("Failed to update film status.");
        }
    }
}

