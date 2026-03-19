package com.ezequielmartin.horrorverse_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<MovieOfActorDTO> actorMovies;
}
