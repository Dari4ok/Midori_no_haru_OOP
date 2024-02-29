package controller;

import console.DisplayFilms;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.Film;
import repositories.interfaces.FilmInterface;
import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class FilmController {
    private FilmInterface repo;

    Connection connection;

    public FilmController(FilmInterface filmInterface){
        this.repo = (FilmInterface) filmInterface;
    }

    public String getAllFilms(){
        List<Film> users =  repo.getAllFilms();
        return DisplayFilms.displayFilms(users);
    }

    public String addFilm(Film film) {
        film = repo.addFilm(film);

        return (film == null ? "Error!" : "Film added!" );
    }

    public String deleteFilm(int filmId) {
        repo.deleteFilm(filmId);
        return ("Film deleted!");
    }

    public int getFilmCount() {
        List<Film> films = repo.getAllFilms();
        return films.size();
    }
}
