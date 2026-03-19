package com.ezequielmartin.horrorverse_api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovieCreateRequestDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String synopsis;

    @NotNull
    private LocalDate releaseDate;

    @NotNull
    @Min(1)
    private Integer durationMinutes;

    @NotNull
    private Long directorId;

    private List<Long> genreIds;

    private List<ActorRoleDTO> actors;
}
