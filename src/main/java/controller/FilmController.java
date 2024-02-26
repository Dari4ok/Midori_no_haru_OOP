package controller;

import console.DisplayFilms;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import models.Film;
import repositories.FilmDao;
import repositories.interfaces.FilmInterface;

import java.sql.Connection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class FilmController {
    private FilmDao filmDao;
    private FilmInterface repo;

    Connection connection;

    public FilmController(FilmDao filmDao){
        this.repo = (FilmInterface) filmDao;
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
        Film film;
        repo.deleteFilm(filmId);
        return ("Film deleted!");
    }

    public void resetSequence(String sequenceName, int restartValue) {
        repo.resetSequence(sequenceName, restartValue);
    }

    public static void exitProgram() {
        System.exit(0);
    }
}
