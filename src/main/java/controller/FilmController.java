package controller;

import models.Film;
import repositories.interfaces.FilmInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class FilmController {
    private final FilmInterface repo;
    Connection connection;

    public FilmController(FilmInterface repo){
        this.repo = repo;
    }

    public String getAllFilms(){
        List<Film> users =  repo.getAllFilms();

        StringBuilder response = new StringBuilder();
        for(Film film : users){
            response.append(users.toString()).append("\n");
        }

        return response.toString();
    }


}
