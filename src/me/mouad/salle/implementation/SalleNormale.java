package me.mouad.salle.implementation;

import me.mouad.salle.Salle;

public class SalleNormale extends Salle {
    public SalleNormale(int number, String name, int places) {
        super(number, name, places);
    }

    @Override
    public double getPrice() {
        return 30;
    }
}
