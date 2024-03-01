package console;

import models.Film;

import java.util.List;

public class DisplayFilms {

    // this code will output our database to console like table
    public static String displayFilms(List<Film> filmtable) {
        System.out.printf("%-5s | %-20s | %-20s | %-20s | %-20s\n", "ID", "Film Name", "Director", "Genres", "Status");
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Film film : filmtable) {
            System.out.printf("%-5d | %-20s | %-20s | %-20s | %-20s\n", film.getId(), film.getFilm_name(),
                    film.getDirector(), film.getGenre(), film.getMy_list());
        }
        return "";
    }
}
