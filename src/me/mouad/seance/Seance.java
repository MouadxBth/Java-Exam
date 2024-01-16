package me.mouad.seance;

import me.mouad.exceptions.PlaceIndisponibleException;
import me.mouad.film.Film;
import me.mouad.salle.Salle;

import java.io.Serializable;
import java.util.Date;

public class Seance implements Serializable {
    private final Film film;
    private final Salle salle;
    private final Date projection;
    private int placesSolde;

    public Seance(Film film, Salle salle, Date projection, int placesSolde) {
        this.film = film;
        this.salle = salle;
        this.projection = projection;
        this.placesSolde = placesSolde;
    }

    public void sellPlaces(int places) {
        placesSolde += places;
    }

    public Film getFilm() {
        return film;
    }

    public Salle getSalle() {
        return salle;
    }

    public Date getProjection() {
        return projection;
    }

    public int getPlacesSolde() {
        return placesSolde;
    }
}
