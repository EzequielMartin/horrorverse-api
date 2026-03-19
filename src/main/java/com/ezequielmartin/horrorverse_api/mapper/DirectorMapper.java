package com.ezequielmartin.horrorverse_api.mapper;

import com.ezequielmartin.horrorverse_api.dto.DirectorCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.DirectorResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieOfDirectorDTO;
import com.ezequielmartin.horrorverse_api.model.Director;
import com.ezequielmartin.horrorverse_api.model.Movie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DirectorMapper {

    public DirectorResponseDTO mapDirectorToDirectorResponseDTO(Director director) {
        DirectorResponseDTO directorResponseDTO = new DirectorResponseDTO();
        directorResponseDTO.setId(director.getId());
        directorResponseDTO.setFirstName(director.getFirstName());
        directorResponseDTO.setLastName(director.getLastName());

        List<MovieOfDirectorDTO> moviesOfDirector = new ArrayList<>();
        for (Movie movie : director.getMovies()) {
            MovieOfDirectorDTO movieOfDirectorDTO = new MovieOfDirectorDTO();
            movieOfDirectorDTO.setMovieId(movie.getId());
            movieOfDirectorDTO.setTitle(movie.getTitle());
            moviesOfDirector.add(movieOfDirectorDTO);
        }
        directorResponseDTO.setMovies(moviesOfDirector);

        return directorResponseDTO;
    }

    public Director mapDirectorCreateRequestToDirector(DirectorCreateRequestDTO directorCreateRequestDTO) {
        Director director = new Director();
        director.setFirstName(directorCreateRequestDTO.getFirstName());
        director.setLastName(directorCreateRequestDTO.getLastName());
        return director;
    }
}
