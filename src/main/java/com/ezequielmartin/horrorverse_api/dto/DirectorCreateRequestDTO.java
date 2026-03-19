package com.ezequielmartin.horrorverse_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DirectorCreateRequestDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
