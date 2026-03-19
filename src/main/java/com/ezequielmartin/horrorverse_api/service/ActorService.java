package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.ActorCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.ActorResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.ActorUpdateRequestDTO;
import com.ezequielmartin.horrorverse_api.mapper.ActorMapper;
import com.ezequielmartin.horrorverse_api.model.Actor;
import com.ezequielmartin.horrorverse_api.repository.IActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService implements IActorService {

    private final IActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Transactional(readOnly = true)
    @Override
    public List<ActorResponseDTO> getActors() {
        List<Actor> actors = actorRepository.findAll();
        List<ActorResponseDTO> actorResponseDTOList = new ArrayList<>();
        for (Actor actor : actors) {
            ActorResponseDTO actorResponseDTO = actorMapper.mapActorToActorResponseDTO(actor);
            actorResponseDTOList.add(actorResponseDTO);
        }
        return actorResponseDTOList;
    }

    @Transactional(readOnly = true)
    @Override
    public ActorResponseDTO getActorById(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Actor not found"));
        return actorMapper.mapActorToActorResponseDTO(actor);
    }

    @Transactional
    @Override
    public ActorResponseDTO createActor(ActorCreateRequestDTO actorCreateRequestDTO) {
        Actor actor = actorMapper.mapActorCreateRequestDTOToActor(actorCreateRequestDTO);
        actorRepository.save(actor);
        return actorMapper.mapActorToActorResponseDTO(actor);
    }

    @Transactional
    @Override
    public ActorResponseDTO updateActor(Long id, ActorUpdateRequestDTO actorUpdateRequestDTO) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Actor not found"));

        if (actorUpdateRequestDTO.getFirstName() != null) {
            actor.setFirstName(actorUpdateRequestDTO.getFirstName());
        }
        if (actorUpdateRequestDTO.getLastName() != null) {
            actor.setLastName(actorUpdateRequestDTO.getLastName());
        }

        actorRepository.save(actor);

        return actorMapper.mapActorToActorResponseDTO(actor);
    }

    @Transactional
    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
