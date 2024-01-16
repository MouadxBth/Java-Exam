package me.mouad;

import me.mouad.film.Film;
import me.mouad.salle.implementation.SalleNormale;
import me.mouad.seance.Seance;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        final Cinema cinema = new Cinema();
        final Film film = new Film("X3D", "mouad");
        final SalleNormale salleNormale = new SalleNormale(4, "Normal", 100);

        cinema.loadFilms("./cinema.txt");

        cinema.getFilms().forEach(f -> System.out.println(f.getTitle() + " " + f.getProducer()));

        cinema.addFilm(film);
        cinema.addSalle(salleNormale);
        cinema.addSeance(new Seance(film, salleNormale, new Date(), 14));

        System.out.println("Total Revenue: " + cinema.totalRevenue());
        System.out.println("Total filling of: " + film.getTitle() + " is: " + cinema.filmFillingPercentage(film.getTitle()));

        cinema.save("./saved.txt");
    }
}