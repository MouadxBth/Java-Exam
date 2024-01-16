package me.mouad;

import me.mouad.exceptions.PlaceIndisponibleException;
import me.mouad.film.Film;
import me.mouad.salle.Salle;
import me.mouad.salle.implementation.SalleNormale;
import me.mouad.seance.Seance;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;

public class Cinema implements IVendeurCinema, IAdminCinema, Serializable {
    private final HashSet<Salle> salles = new HashSet<>();
    private final HashSet<Film> films = new HashSet<>();
    private final HashSet<Seance> seances = new HashSet<>();

    public HashSet<Film> getFilms() {
        return films;
    }

    public Film findFilm(String name) {
        return films.stream()
                .filter(film -> film.getTitle().equals(name))
                .findAny()
                .orElse(null);
    }

    public Salle findSalle(int number) {
        return salles.stream()
                .filter(salle -> salle.getNumber() == number)
                .findAny()
                .orElse(null);
    }

    public Collection<Film> getProgrammedFilms() {
        return seances.stream()
                .map(Seance::getFilm)
                .toList();
    }

    public void buyPlace(String film) throws PlaceIndisponibleException{
        final Seance result = seances
                .stream()
                .filter(value -> value.getFilm().getTitle().equals(film))
                .findAny()
                .orElse(null);

        if (result == null) throw new PlaceIndisponibleException();

        if (result.getPlacesSolde() + 1 < result.getSalle().getPlaces()) throw new PlaceIndisponibleException();

        result.sellPlaces(1);
    }

    public void sellPlaces(int places, String film) throws PlaceIndisponibleException {
        final Seance result = seances
                .stream()
                .filter(value -> value.getFilm().getTitle().equals(film))
                .findAny()
                .orElse(null);

        if (result == null) throw new PlaceIndisponibleException();

        if (result.getPlacesSolde() + places < result.getSalle().getPlaces()) throw new PlaceIndisponibleException();

        result.sellPlaces(places);
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void addSalle(Salle salle) {
        salles.add(salle);
    }

    public void addSeance(Seance seance) {
        seances.add(seance);
    }

    public double totalRevenue() {
        return seances.stream().mapToDouble(seance -> {
            if (seance.getSalle() instanceof SalleNormale)
                return seance.getPlacesSolde() * 30;
            return seance.getPlacesSolde() * 60;
        }).sum();
    }

    public double filmFillingPercentage(String film) {
        final Collection<Seance> result = seances.stream()
                .filter(seance -> seance.getFilm().getTitle().equals(film)).toList();

        return (result.stream()
                .mapToDouble(seance -> ((double) seance.getPlacesSolde()) / seance.getSalle().getPlaces())
                .sum() / result.size()) * 100;

    }

    public void loadFilms(String filename) {
        File file = new File(filename);

        try {
            FileReader reader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            String[] splitLine;

            while ((line = bufferedReader.readLine()) != null) {
                splitLine = line.split(";");
                addFilm(new Film(splitLine[0], splitLine[1]));
            }

            reader.close();
            bufferedReader.close();
        } catch (Exception e) {
            System.err.println("Unable to load films from file: " + filename + " due to " + e.getMessage());
        }
    }

    public void save(String filename) {
        File file = new File(filename);

        try {
            FileOutputStream outputStream = new FileOutputStream(file);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();
        } catch (Exception e) {
            System.err.println("Unable to save cinema to file: " + filename + " due to " + e.getMessage());
        }

    }
}
