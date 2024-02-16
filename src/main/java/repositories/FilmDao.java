package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.Film;

    public class FilmDao {

        //Connectin to db
        private Connection connection;

        public FilmDao(String connectionString, String username, String password) {
            try {
                connection = DriverManager.getConnection(connectionString, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Output all list from database
        public List<Film> getAllFilms() {
            List<Film> users = new ArrayList<>();
            String sql = "SELECT id, film_name, director, genres, my_list FROM users ORDER BY id";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String filmName = resultSet.getString("film_name");
                    String director = resultSet.getString("director");
                    String genres = resultSet.getString("genres");
                    String myList = resultSet.getString("my_list");
                    Film user = new Film(id, filmName, director, genres, myList);
                    users.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return users;
        }

        // This code add new film
        public void addFilm(Film film) {
            String query = "INSERT INTO users (film_name, director, genres, my_list) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, film.getFilm_name());
                statement.setString(2, film.getDirector());
                statement.setString(3, film.getGenre());
                statement.setString(4, film.getMy_list());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // this deleting film from the table
        public void deleteFilm(int filmId) {
            String query = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, filmId);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        //This code for deleting by ID (now only for deleteFilm())
        public Film getFilmById(int filmId) {
            Film film = null;
            String query = "SELECT * FROM users WHERE id = ?";
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simpledb",
                    "postgres", "0000");
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, filmId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    film = new Film(resultSet.getInt("id"),
                            resultSet.getString("film_name"),
                            resultSet.getString("director"),
                            resultSet.getString("genres"),
                            resultSet.getString("my_list"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return film;
        }

        // This is for setting new ID
        public void resetSequence(String sequenceName, int restartValue) {
            String query = "ALTER SEQUENCE " + sequenceName + " RESTART WITH " + restartValue;
            try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/simpledb",
                    "postgres", "0000");
                 Statement statement = connection.createStatement()) {
                statement.execute(query);
                System.out.println("Sequence " + sequenceName + " reset to start with " + restartValue);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Exciting from the program
        public static void exitProgram() {
            System.out.println("Exiting the program. Goodbye!");
            // Any cleanup or closing of resources can be done here
            System.exit(0);
        }

        // Need update function
        // Other methods for adding, updating, and deleting films can be added here
    }
