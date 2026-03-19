package com.ezequielmartin.horrorverse_api.mapper;

import com.ezequielmartin.horrorverse_api.dto.GenreCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.GenreResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieOfGenreDTO;
import com.ezequielmartin.horrorverse_api.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    public GenreResponseDTO mapGenreToGenreResponseDTO(Genre genre) {
        GenreResponseDTO genreResponseDTO = new GenreResponseDTO();
        genreResponseDTO.setId(genre.getId());
        genreResponseDTO.setGenreName(genre.getName());
        genreResponseDTO.setMovies(genre.getMovies()
                .stream()
                .map(m -> {
                    MovieOfGenreDTO movieOfGenreDTO = new MovieOfGenreDTO();
                    movieOfGenreDTO.setMovieId(m.getId());
                    movieOfGenreDTO.setTitle(m.getTitle());
                    return movieOfGenreDTO;
                })
                .toList());
        return genreResponseDTO;
    }

    public Genre mapGenreCreateRequestDTOToGenre(GenreCreateRequestDTO genreCreateRequestDTO) {
        Genre genre = new Genre();
        genre.setName(genreCreateRequestDTO.getName());
        return genre;
    }
}
