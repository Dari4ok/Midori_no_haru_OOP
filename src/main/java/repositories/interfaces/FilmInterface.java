package repositories.interfaces;

import Manage.Interfaces.Model;
import models.Film;

import java.util.List;

public interface FilmInterface {
    List<Film> getAllFilms();
    Film addFilm(Film film);
    void deleteFilm(int filmid);
    boolean updateStatus(int id, String newStatus);
    int getFilmCount();



    Film getFilmById(int filmid);


    Model get(int id);

    boolean insert(Model model);

    boolean updateStatus(Model model);

    boolean delete(int id);
}
