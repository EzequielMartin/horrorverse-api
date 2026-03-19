package com.ezequielmartin.horrorverse_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieOfActorDTO {
    private Long movieId;
    private String movieTitle;
    private String roleFirstName;
    private String roleLastName;
}
