package repositories;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
        List<Film> filmtable = new ArrayList<>();
        String sql = "SELECT * FROM filmtable ORDER BY id";
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
                filmtable.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmtable;
    }

    // This code add new film
    @Override
    public Film addFilm(Film film) {
        String query = "INSERT INTO filmtable (film_name, director, genres, my_list) VALUES (?, ?, ?, ?)";

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
        String query = "DELETE FROM filmtable WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, filmId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateStatus(int id, String newStatus) {
        return false;
    }

    @Override
    public int getFilmCount() {
        return 0;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
        // Other methods for adding, updating, and deleting films can be added here
}
