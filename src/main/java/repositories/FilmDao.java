package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Manage.Interfaces.Model;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.Film;
import repositories.interfaces.FilmInterface;


@AllArgsConstructor
@NoArgsConstructor
public class FilmDao implements FilmInterface{
    // Connection to database
    private Connection connection;

    public FilmDao(String connectionString, String username, String password) {
        try{
            this.connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Film> getAllFilms() {
        List<Film> users = new ArrayList<>();
        String sql = "SELECT id, film_name, director, genres, my_list FROM users ORDER BY id";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String filmName = resultSet.getString("film_name");
                String director = resultSet.getString("director");
                String genres = resultSet.getString("genres");
                String myList = resultSet.getString("my_list");
                Film film = new Film(id, filmName, director, genres, myList);
                users.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // This code add new film
    @Override
    public Film addFilm(Film film) {
        String query = "INSERT INTO users (film_name, director, genres, my_list) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, film.getFilm_name());
            statement.setString(2, film.getDirector());
            statement.setString(3, film.getGenre());
            statement.setString(4, film.getMy_list());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return film;
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

    public Film

    getFilmById(int filmId) {
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

    @Override
    public boolean updateStatus(int id, String newStatus) {
        return false;
    }

    @Override
    public Model get(int id) {
        return null;
    }

    @Override
    public boolean insert(Model model) {
        return false;
    }

    @Override
    public boolean updateStatus(Model model) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public int getFilmCount() {
        List<Film> films = getAllFilms();
        return films.size();
    }

    // Other methods for adding, updating, and deleting films can be added here
}
