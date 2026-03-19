package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.ActorCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.ActorResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.ActorUpdateRequestDTO;

import java.util.List;

public interface IActorService {

    public List<ActorResponseDTO> getActors();
    public ActorResponseDTO getActorById(Long id);
    public ActorResponseDTO createActor(ActorCreateRequestDTO actorCreateRequestDTO);
    public ActorResponseDTO updateActor(Long id, ActorUpdateRequestDTO actorUpdateRequestDTO);
    public void deleteActor(Long id);
}
