import general.film;

import java.sql.*;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        ArrayList<film> users = new ArrayList<>();
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionString, "postgres", "0000");

            String sql = "SELECT id, film_name, director, genres, my_list FROM users ORDER BY id;";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String film_name = rs.getString("film_name");
                String director = rs.getString("director");
                String genre = rs.getString("genres");
                String my_list = rs.getString("my_list");

                film film = new film(id, film_name, director, genre, my_list);
                users.add(film);
            }
        } catch (SQLException e) {
            System.out.println("connection error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("driver error: " + e.getMessage());
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("could not close the connection: " + e.getMessage());
                }
            }
        }
        System.out.printf("%-5s | %-20s | %-20s | %-20s | %-20s\n", "ID", "Film Name", "Director", "Genres", "My List");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (film film : users) {
            System.out.printf("%-5d | %-20s | %-20s | %-20s | %-20s\n", film.getId(), film.getFilm_name(),
                    film.getDirector(), film.getGenre(), film.getMy_list());
        }
    }
}