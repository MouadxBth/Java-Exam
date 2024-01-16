package me.mouad.exceptions;

public class PlaceIndisponibleException extends Exception {
    public PlaceIndisponibleException() {
        super("No more places!");
    }
}
