package com.ezequielmartin.horrorverse_api.dto;

import com.ezequielmartin.horrorverse_api.model.MovieActor;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorCreateRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
