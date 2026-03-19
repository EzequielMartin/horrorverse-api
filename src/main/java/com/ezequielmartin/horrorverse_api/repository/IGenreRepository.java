package com.ezequielmartin.horrorverse_api.repository;

import com.ezequielmartin.horrorverse_api.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository extends JpaRepository<Genre,Long> {
}
