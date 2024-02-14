package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import general.Film;

    public class FilmDao {
        private Connection connection;

        public FilmDao(String connectionString, String username, String password) {
            try {
                connection = DriverManager.getConnection(connectionString, username, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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

        // Other methods for adding, updating, and deleting films can be added here
    }
