package com.ezequielmartin.horrorverse_api.mapper;

import com.ezequielmartin.horrorverse_api.dto.ActorCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.ActorResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.MovieOfActorDTO;
import com.ezequielmartin.horrorverse_api.model.Actor;
import org.springframework.stereotype.Component;

@Component
public class ActorMapper {

    public ActorResponseDTO mapActorToActorResponseDTO(Actor actor) {
        ActorResponseDTO actorResponseDTO = new ActorResponseDTO();
        actorResponseDTO.setId(actor.getId());
        actorResponseDTO.setFirstName(actor.getFirstName());
        actorResponseDTO.setLastName(actor.getLastName());
        actorResponseDTO.setActorMovies(actor.getActorMovies().stream().map(m -> {
            MovieOfActorDTO movieOfActorDTO = new MovieOfActorDTO();
            movieOfActorDTO.setMovieTitle(m.getMovie().getTitle());
            movieOfActorDTO.setMovieId(m.getMovie().getId());
            movieOfActorDTO.setRoleFirstName(m.getRoleFirstName());
            movieOfActorDTO.setRoleLastName(m.getRoleLastName());
            return movieOfActorDTO;
        }).toList());
        return actorResponseDTO;
    }

    public Actor mapActorCreateRequestDTOToActor(ActorCreateRequestDTO actorCreateRequestDTO) {
        Actor actor = new Actor();
        actor.setFirstName(actorCreateRequestDTO.getFirstName());
        actor.setLastName(actorCreateRequestDTO.getLastName());
        return actor;
    }
}
