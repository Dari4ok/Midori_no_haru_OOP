import database.FilmDao;
import general.Film;
import java.util.List;

public class main {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/simpledb";
        String username = "postgres";
        String password = "0000";

        FilmDao filmDao = new FilmDao (connectionString, username, password);
        List<Film> users = filmDao.getAllFilms();

        displayFilms(users);
    }

    private static void displayFilms(List<Film> users) {
        System.out.printf("%-5s | %-20s | %-20s | %-20s | %-20s\n", "ID", "Film Name", "Director", "Genres", "Status");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Film film : users) {
            System.out.printf("%-5d | %-20s | %-20s | %-20s | %-20s\n", film.getId(), film.getFilm_name(),
                    film.getDirector(), film.getGenre(), film.getMy_list());
        }
    }
}
