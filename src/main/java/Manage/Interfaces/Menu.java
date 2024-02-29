package Manage.Interfaces;

import repositories.interfaces.FilmInterface;

public interface Menu {
    void launchMenu(FilmInterface filmInterface, int userId, Object manager);
}
