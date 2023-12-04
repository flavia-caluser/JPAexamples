package com.springapps.jpaexamples.MovieApp;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue
    private long characterId;

    @Column
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "character_movies",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies;

    public Character() {
    }

    public Character( String name, Set<Movie> movies) {
        this.name = name;
        this.movies = movies;
    }

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "Character{" +
                "characterId=" + characterId +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }
}
