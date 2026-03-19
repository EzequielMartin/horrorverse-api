package com.ezequielmartin.horrorverse_api.repository;

import com.ezequielmartin.horrorverse_api.model.MovieActor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieActorRepository extends JpaRepository<MovieActor,Long> {
}
