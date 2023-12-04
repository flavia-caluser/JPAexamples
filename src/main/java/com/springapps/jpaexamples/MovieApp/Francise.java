package com.springapps.jpaexamples.MovieApp;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Francise {

    @Id
    @GeneratedValue
    private long franciseId;

    @Column
    private String name;

    @OneToMany(mappedBy = "francise")
    private Set<Movie> movies;

    public Francise() {
    }

    public Francise(long franciseId, String name, Set<Movie> movies) {
        this.franciseId = franciseId;
        this.name = name;
        this.movies = movies;
    }

    public long getFranciseId() {
        return franciseId;
    }

    public void setFranciseId(long franciseId) {
        this.franciseId = franciseId;
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
        return "Francise{" +
                "franciseId=" + franciseId +
                ", name='" + name + '\'' +
                ", movies=" + movies +
                '}';
    }


}
