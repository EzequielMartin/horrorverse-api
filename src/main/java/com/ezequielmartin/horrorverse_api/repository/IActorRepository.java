package com.ezequielmartin.horrorverse_api.repository;

import com.ezequielmartin.horrorverse_api.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorRepository extends JpaRepository<Actor,Long> {
}
