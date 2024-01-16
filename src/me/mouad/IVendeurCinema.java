package me.mouad;

import me.mouad.exceptions.PlaceIndisponibleException;
import me.mouad.seance.Seance;

public interface IVendeurCinema extends IUserCinema {

    void sellPlaces(int places, String film) throws PlaceIndisponibleException;

}
