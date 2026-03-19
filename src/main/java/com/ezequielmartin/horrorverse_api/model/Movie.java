package com.ezequielmartin.horrorverse_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Length(max = 100)
    private String title;

    @NotBlank @Length(max = 500)
    private String synopsis;

    @NotNull
    private LocalDate releaseDate;

    @NotNull @Min(1)
    private Integer durationMinutes;

    @ManyToMany(fetch = FetchType.LAZY)
    //El JoinTable solo lo hago en la clase "dueña" de la relación manytomany, en Genre voy a usar mappedBy solamente
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "movie",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<MovieActor> movieActors;

    private String posterUrl;
    private Double rating;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

}
