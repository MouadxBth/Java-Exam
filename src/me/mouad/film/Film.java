package me.mouad.film;

import java.io.Serializable;

public class Film implements Serializable {
    private final String title, producer;

    public Film(String title, String producer) {
        this.title = title;
        this.producer = producer;
    }

    public String getTitle() {
        return title;
    }

    public String getProducer() {
        return producer;
    }
}
