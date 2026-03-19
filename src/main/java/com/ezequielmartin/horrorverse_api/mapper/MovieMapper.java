package com.ezequielmartin.horrorverse_api.mapper;

import com.ezequielmartin.horrorverse_api.dto.ActorInMovieDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieResponseDTO;
import com.ezequielmartin.horrorverse_api.model.Director;
import com.ezequielmartin.horrorverse_api.model.Genre;
import com.ezequielmartin.horrorverse_api.model.Movie;
import com.ezequielmartin.horrorverse_api.model.MovieActor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieMapper {

    public MovieResponseDTO movieToMovieResponseDTO(Movie movie) {

        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();

        movieResponseDTO.setId(movie.getId());
        movieResponseDTO.setTitle(movie.getTitle());
        movieResponseDTO.setSynopsis(movie.getSynopsis());
        movieResponseDTO.setReleaseDate(movie.getReleaseDate());
        movieResponseDTO.setDurationMinutes(movie.getDurationMinutes());
        movieResponseDTO.setDirectorName(movie.getDirector().getFirstName() + " " + movie.getDirector().getLastName());
        movieResponseDTO.setActors(
                movie.getMovieActors()
                        .stream()
                        .map(a -> {
                            ActorInMovieDTO actorDTO = new ActorInMovieDTO();
                            actorDTO.setActorName(
                                    a.getActor().getFirstName() + " " + a.getActor().getLastName()
                            );
                            actorDTO.setRoleName(
                                    a.getRoleFirstName() + " "  + a.getRoleLastName()
                            );
                            return actorDTO;
                        })
                        .toList()
        );
        movieResponseDTO.setGenres(
                movie.getGenres().stream().map(
                        g -> g.getName()
                        )
                        .toList()
        );

        return movieResponseDTO;
    }

    public Movie movieCreateRequestToMovie(MovieCreateRequestDTO movieCreateRequestDTO,
                                           Director director,
                                           List<Genre> genres,
                                           List<MovieActor> movieActors) {
        Movie movie = new Movie();

        movie.setTitle(movieCreateRequestDTO.getTitle());
        movie.setSynopsis(movieCreateRequestDTO.getSynopsis());
        movie.setReleaseDate(movieCreateRequestDTO.getReleaseDate());
        movie.setDurationMinutes(movieCreateRequestDTO.getDurationMinutes());
        movie.setDirector(director);
        movie.setGenres(genres);
        movie.setMovieActors(movieActors);

        return movie;
    }
}
