package repositories.interfaces;

import models.Film;

import java.util.List;

public interface FilmInterface {
    List<Film> getAllFilms();
    Film addFilm(Film film);
    void deleteFilm(int filmid);
    boolean updateStatus(int id, String newStatus);
    int getFilmCount();
    boolean delete(int id);
}
