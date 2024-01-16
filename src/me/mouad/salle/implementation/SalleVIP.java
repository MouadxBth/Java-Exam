package me.mouad.salle.implementation;

import me.mouad.salle.Salle;

public class SalleVIP extends Salle {
    public SalleVIP(int number, String name, int places) {
        super(number, name, places);
    }

    @Override
    public double getPrice() {
        return 60;
    }
}
