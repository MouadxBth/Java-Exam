package me.mouad.interfaces;

import me.mouad.exceptions.PlaceIndisponibleException;
import me.mouad.film.Film;
import me.mouad.salle.Salle;
import me.mouad.seance.Seance;

import java.util.Collection;

public interface IUserCinema {
    Film findFilm(String name);

    Salle findSalle(int number);

    Collection<Film> getProgrammedFilms();

    void buyPlace(String film) throws PlaceIndisponibleException;
}
