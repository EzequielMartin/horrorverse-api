package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.ActorRoleDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieUpdateRequestDTO;
import com.ezequielmartin.horrorverse_api.mapper.MovieMapper;
import com.ezequielmartin.horrorverse_api.model.*;
import com.ezequielmartin.horrorverse_api.repository.IActorRepository;
import com.ezequielmartin.horrorverse_api.repository.IDirectorRepository;
import com.ezequielmartin.horrorverse_api.repository.IGenreRepository;
import com.ezequielmartin.horrorverse_api.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService implements IMovieService {

    private final IMovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final IDirectorRepository directorRepository;
    private final IGenreRepository iGenreRepository;
    private final IActorRepository iActorRepository;

    //Con For clasico
//    @Override
//    public List<MovieResponseDTO> getMovies() {
//        List<Movie> movies =  movieRepository.findAll();
//        List<MovieResponseDTO> movieResponseDTOS = new ArrayList<>();
//        for (Movie movie : movies) {
//            MovieResponseDTO movieResponseDTO = movieMapper.movieToMovieResponseDTO(movie);
//            movieResponseDTOS.add(movieResponseDTO);
//        }
//        return movieResponseDTOS;
//    }

    //Con programacion funcional pero sin referencia a metodos
//    @Override
//    public List<MovieResponseDTO> getMovies() {
//        List<Movie> movies = movieRepository.findAll();
//        List<MovieResponseDTO> movieDTOs = movies.stream().map(m -> movieMapper.movieToMovieResponseDTO(m)).toList();
//        return movieDTOs;
//    }

    //Con programacion funcional y referencia a metodos
    @Transactional(readOnly = true)
    @Override
    public List<MovieResponseDTO> getMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieResponseDTO> movieResponseDTOS = movies.stream()
                .map(movieMapper::movieToMovieResponseDTO)
                .toList();
        return  movieResponseDTOS;
    }

    @Transactional(readOnly = true)
    @Override
    public MovieResponseDTO getMovie(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::movieToMovieResponseDTO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found"));
    }

    @Transactional
    @Override
    public MovieResponseDTO createMovie(MovieCreateRequestDTO movieCreateRequestDTO) {

        //Busco el director
        Director director = directorRepository.findById(movieCreateRequestDTO.getDirectorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Director not found"));

        //Busco los generos
        List<Genre> genres = iGenreRepository.findAllById(movieCreateRequestDTO.getGenreIds());
        if (genres.size() != movieCreateRequestDTO.getGenreIds().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Some genres not found");
        }

        //Creo los movieActors
        List<MovieActor> movieActors = new ArrayList<>();

        for (ActorRoleDTO actorRoleDTO : movieCreateRequestDTO.getActors()) {
            Actor actor = iActorRepository.findById(actorRoleDTO.getActorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Actor not found"));

            MovieActor movieActor = new MovieActor();
            movieActor.setActor(actor);
            movieActor.setRoleFirstName(actorRoleDTO.getRoleFirstName());
            movieActor.setRoleLastName(actorRoleDTO.getRoleLastName());

            movieActors.add(movieActor);
        }

        //Creo la movie con mapper
        Movie movie = movieMapper.movieCreateRequestToMovie(movieCreateRequestDTO, director, genres, movieActors);

        //A cada movie actor le guardo la película, ya le había guardado el actor en el for anterior
        for (MovieActor movieActor : movieActors) {
            movieActor.setMovie(movie);
        }

        //Guardo la película
        Movie savedMovie = movieRepository.save(movie);

        //Convertir a DTO y generar respuesta
        return movieMapper.movieToMovieResponseDTO(savedMovie);
    }

    @Transactional
    @Override
    public MovieResponseDTO updateMovie(Long id, MovieUpdateRequestDTO movieUpdateRequestDTO) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Movie not found"));

        if (movieUpdateRequestDTO.getTitle() != null) {
            movie.setTitle(movieUpdateRequestDTO.getTitle());
        }

        if (movieUpdateRequestDTO.getSynopsis() != null) {
            movie.setSynopsis(movieUpdateRequestDTO.getSynopsis());
        }

        if (movieUpdateRequestDTO.getReleaseDate() != null) {
            movie.setReleaseDate(movieUpdateRequestDTO.getReleaseDate());
        }

        if (movieUpdateRequestDTO.getDurationMinutes() != null) {
            movie.setDurationMinutes(movieUpdateRequestDTO.getDurationMinutes());
        }

        if (movieUpdateRequestDTO.getGenreIds() != null) {
            List<Genre> genres = iGenreRepository.findAllById(movieUpdateRequestDTO.getGenreIds());
            movie.setGenres(genres);
        }

        if (movieUpdateRequestDTO.getDirectorId() != null) {
            Director director = directorRepository.findById(movieUpdateRequestDTO.getDirectorId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Director not found"));
            movie.setDirector(director);
        }

        if (movieUpdateRequestDTO.getActors() != null) {
            List<MovieActor> movieActors = new ArrayList<>();

            for (ActorRoleDTO actorRoleDTO : movieUpdateRequestDTO.getActors()) {
                Actor actor = iActorRepository.findById(actorRoleDTO.getActorId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Actor not found"));

                MovieActor movieActor = new MovieActor();
                movieActor.setMovie(movie);
                movieActor.setActor(actor);
                movieActor.setRoleFirstName(actorRoleDTO.getRoleFirstName());
                movieActor.setRoleLastName(actorRoleDTO.getRoleLastName());
                movieActors.add(movieActor);
            }

            movie.setMovieActors(movieActors);
        }

        Movie updatedMovie = movieRepository.save(movie);

        return movieMapper.movieToMovieResponseDTO(updatedMovie);
    }

    @Transactional
    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
