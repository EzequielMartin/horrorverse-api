package com.ezequielmartin.horrorverse_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    private List<MovieActor> actorMovies;

}
