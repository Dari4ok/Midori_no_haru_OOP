package Manage.Menu;

import Manage.Interfaces.Menu;
import console.DisplayFilms;
import controller.FilmController;
import models.Film;
import repositories.interfaces.FilmInterface;
import java.util.List;
import java.util.Scanner;

public class AdminMenu implements Menu {
    private FilmController controller;

    public AdminMenu(FilmController filmController) {
    }

    public void launchMenu(FilmInterface filmInterface) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Admin menu:");
            System.out.println("1. View all films");
            System.out.println("2. Add a new film");
            System.out.println("3. Update status ");
            System.out.println("4. Delete a film");
            System.out.println("5. Sign out");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showAllFilms(filmInterface, controller);
                    break;
                case 2:
                    addFilm(scanner);
                    break;
                case 3:
                    updateFilmStatus(filmInterface, scanner);
                    break;
                case 4:
                    deleteFilm(filmInterface, scanner);
                    break;
                case 5:
                    exit = true;
                    break;
                case 6:
                    showFilmCount(filmInterface);
                    break;
                default:
                    System.out.println("Please choose between 1-5");
            }
        }
    }

    private void showAllFilms(FilmInterface filmInterface, FilmController controller) {
        List<Film> films = filmInterface.getAllFilms();
        System.out.println("Films: ");
        for (Film film: films) {
            System.out.println((film).toString());
        }
        controller.getAllFilms();
        System.out.println();
    }

    private void addFilm(Scanner scanner) {
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

    private void deleteFilm(FilmInterface filmInterface, Scanner scanner) {
        List<Film> films = filmInterface.getAllFilms();
        System.out.println("Deleting a film!");
        System.out.println("Enter the ID of the film you want to delete:");
        for (Film film: films) {
            System.out.println(film);
        }
        System.out.println("Enter the ID of the film you want to delete:");
        int id = scanner.nextInt();

        if(filmInterface.delete(id)) {
            System.out.println("Successfully deleted!");
        } else {
            throw new RuntimeException("Error deleted!");
        }
    }

    private void showFilmCount(FilmInterface filmInterface) {
        int filmCount = filmInterface.getFilmCount();
        System.out.println("Total number of films: " + filmCount);
    }

    @Override
    public void launchMenu() {
    }
}
