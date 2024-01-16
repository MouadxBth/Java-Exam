package me.mouad.salle;

import java.io.Serializable;

public abstract class Salle implements Serializable {
    private final int number;
    private final String name;
    private final int places;

    public Salle(int number, String name, int places) {
        this.number = number;
        this.name = name;
        this.places = places;
    }

    public abstract double getPrice();

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPlaces() {
        return places;
    }
}
