package com.springapps.jpaexamples.MovieApp;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue
    private long movieId;

    @Column
    private String name;

    @ManyToMany(mappedBy = "movies", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Character> characters;

    @ManyToOne
    @JoinColumn(name = "francise_id")
    private Francise francise;

    public Movie() {
    }

    public Movie(long movieId, String name, Set<Character> characters, Francise francise) {
        this.movieId = movieId;
        this.name = name;
        this.characters = characters;
        this.francise = francise;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }

    public Francise getFrancise() {
        return francise;
    }

    public void setFrancise(Francise francise) {
        this.francise = francise;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", characters=" + characters +
                ", francise=" + francise +
                '}';
    }
}
