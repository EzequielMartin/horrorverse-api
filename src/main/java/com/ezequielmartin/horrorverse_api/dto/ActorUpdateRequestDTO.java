package com.ezequielmartin.horrorverse_api.dto;

import com.ezequielmartin.horrorverse_api.model.MovieActor;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActorUpdateRequestDTO {
    private String firstName;
    private String lastName;
}
