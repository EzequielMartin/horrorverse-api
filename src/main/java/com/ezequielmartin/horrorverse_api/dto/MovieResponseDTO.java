package com.ezequielmartin.horrorverse_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDTO {

    private Long id;
    private String title;
    private String synopsis;
    private LocalDate releaseDate;
    private Integer durationMinutes;
    private String directorName;
    private List<String> genres;
    private List<ActorInMovieDTO> actors;
}
