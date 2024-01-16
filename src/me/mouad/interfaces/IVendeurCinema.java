package me.mouad.interfaces;

import me.mouad.exceptions.PlaceIndisponibleException;

public interface IVendeurCinema extends IUserCinema {

    void sellPlaces(int places, String film) throws PlaceIndisponibleException;

}
