package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.MovieCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieUpdateRequestDTO;

import java.util.List;

public interface IMovieService {

    public List<MovieResponseDTO> getMovies();
    public MovieResponseDTO getMovie(Long id);
    public MovieResponseDTO createMovie(MovieCreateRequestDTO movieCreateRequestDTO);
    public MovieResponseDTO updateMovie(Long id, MovieUpdateRequestDTO movieUpdateRequestDTO);
    public void deleteMovie(Long id);
}
