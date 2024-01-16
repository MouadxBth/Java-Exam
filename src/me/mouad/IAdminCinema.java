package me.mouad;

import me.mouad.film.Film;
import me.mouad.salle.Salle;
import me.mouad.seance.Seance;

public interface IAdminCinema {

    void addFilm(Film film);

    void addSalle(Salle salle);

    void addSeance(Seance seance);

    double totalRevenue();

    double filmFillingPercentage(String film);

    void loadFilms(String filename);

    void save(String filename);

}
