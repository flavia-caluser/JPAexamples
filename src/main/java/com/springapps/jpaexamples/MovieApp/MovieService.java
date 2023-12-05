package com.springapps.jpaexamples.MovieApp;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Movie addCharacterToMovie(Character character, Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie not found for that Id"));
        movie.getCharacters().add(character);
        character.getMovies().add(movie);
        return movieRepository.save(movie);

    }

    @Transactional
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }


}
