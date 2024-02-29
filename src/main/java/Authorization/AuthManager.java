package Authorization;

import Manage.Menu.AdminMenu;
import Manage.Menu.UserMenu;
import Manage.Role;
import Manage.User;
import data.PostgreDB;
import repositories.FilmDao;
import java.sql.*;
import java.util.Scanner;

public class AuthManager {

    public static User user = null;

    public static void signIn(Scanner scanner) {
        boolean exit = false;
        while (!exit) {
            System.out.print("Username: ");
            String username = scanner.next();
            System.out.print("Password: ");
            String password = scanner.next();
            FilmDao filmDao = new FilmDao();
            if (getFromDB(username, password)) {
                System.out.println("Successfully authorized!");
                exit = true;
                if (user.getRole() == Role.Admin.getId()) {
                    new AdminMenu().launchMenu(filmDao);
                } else {
                    new UserMenu().launchMenu(filmDao);
                }
            } else {
                System.out.println("Incorrect username or password!");
                System.out.println("Do you want to retype username and password again?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                int choice = scanner.nextInt();
                if (choice == 2) {
                    exit = true;
                } else if (choice != 1) {
                    System.out.println("Invalid choice.");
                }
            }
        }
    }

    public static void signUp(Scanner scanner) {
        User newUser = new User();
        System.out.print("Enter username: ");
        newUser.setUsername(scanner.next());
        System.out.print("Enter password: ");
        newUser.setPassword(scanner.next());
        System.out.println("Choose your role: ");
        System.out.println("1. Admin");
        System.out.println("2. User");
        int role = scanner.nextInt();
        if (role == 1) {
            newUser.setRole(Role.Admin.getId());
        } else if (role == 2) {
            newUser.setRole(Role.User.getId());
        } else {
            System.out.println("Invalid choice! You're assigned as a user.");
            newUser.setRole(Role.User.getId());
        }
        FilmDao filmDao = new FilmDao();
        if (saveToDB(newUser)) {
            System.out.println("Successfully registered!");
            if (user.getRole() == Role.Admin.getId()) {
                new AdminMenu().launchMenu(filmDao);
            } else {
                new UserMenu().launchMenu(filmDao);
            }
        }
    }

    private static boolean getFromDB(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection con = PostgreDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                user = new User(
                        res.getInt("id"),
                        res.getString("username"),
                        res.getString("password"),
                        Role.fromID(res.getInt("role"))
                );
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Error while fetching user data: " + e.getMessage());
            return false;
        }
    }

    private static boolean saveToDB(User user) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection con = PostgreDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getRole());//.getId();
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        user.setId(generatedKeys.getInt(1));
                        AuthManager.user = user;
                        return true;
                    } else {
                        System.out.println("Failed to retrieve auto-generated ID.");
                        return false;
                    }
                }
            } else {
                System.out.println("No rows affected while saving user data.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error while saving user data: " + e.getMessage());
            return false;
        }
    }
}
