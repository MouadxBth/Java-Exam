package me.mouad;

import me.mouad.exceptions.PlaceIndisponibleException;
import me.mouad.film.Film;
import me.mouad.interfaces.IAdminCinema;
import me.mouad.interfaces.IVendeurCinema;
import me.mouad.salle.Salle;
import me.mouad.salle.implementation.SalleNormale;
import me.mouad.seance.Seance;

import java.io.*;
import java.util.*;

public class Cinema implements IVendeurCinema, IAdminCinema, Serializable {
    private final Map<Integer, Salle> salles = new HashMap<>();
    private final Map<String, Film> films = new HashMap<>();
    private final Set<Seance> seances = new HashSet<>();

    public Film findFilm(String name) {
        return films.get(name);
    }

    public Salle findSalle(int number) {
        return salles.get(number);
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
        films.put(film.getTitle(), film);
    }

    public void addSalle(Salle salle) {
        salles.put(salle.getNumber(), salle);
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
