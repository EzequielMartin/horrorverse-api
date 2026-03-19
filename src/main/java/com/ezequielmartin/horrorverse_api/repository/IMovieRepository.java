package com.ezequielmartin.horrorverse_api.repository;

import com.ezequielmartin.horrorverse_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository<Movie,Long> {
}
