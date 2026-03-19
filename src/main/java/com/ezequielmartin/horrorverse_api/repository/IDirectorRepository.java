package com.ezequielmartin.horrorverse_api.repository;

import com.ezequielmartin.horrorverse_api.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDirectorRepository extends JpaRepository<Director,Long> {
}
