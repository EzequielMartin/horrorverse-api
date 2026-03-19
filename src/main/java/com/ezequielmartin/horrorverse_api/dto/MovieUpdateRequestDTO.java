package com.ezequielmartin.horrorverse_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieUpdateRequestDTO {

    private String title;
    private String synopsis;
    private LocalDate releaseDate;
    private Integer durationMinutes;
    private Long directorId;
    private List<Long> genreIds;
    private List<ActorRoleDTO> actors;
}
