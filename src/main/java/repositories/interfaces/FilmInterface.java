package repositories.interfaces;

import models.Film;

import java.util.List;

public interface FilmInterface {
    List<Film> getAllFilms();
    Film addFilm(Film film);
    void deleteFilm(int filmid);
    Film getFilmById(int filmid);
    void resetSequence(String sequenceName, int restartValue);
    void exitProgram();
}
