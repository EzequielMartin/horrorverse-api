package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.DirectorCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.DirectorResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.DirectorUpdateRequestDTO;
import com.ezequielmartin.horrorverse_api.mapper.DirectorMapper;
import com.ezequielmartin.horrorverse_api.model.Director;
import com.ezequielmartin.horrorverse_api.repository.IDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService implements IDirectorService {

    private final IDirectorRepository directorRepository;
    private final DirectorMapper directorMapper;


    @Transactional(readOnly = true)
    @Override
    public DirectorResponseDTO getDirectorById(Long id) {
        Director director = directorRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Director not found"));
        return directorMapper.mapDirectorToDirectorResponseDTO(director);
    }

//    @Transactional(readOnly = true)
//    @Override
//    public List<DirectorResponseDTO> getAll() {
//        List<Director> directors = directorRepository.findAll();
//        List<DirectorResponseDTO> directorResponseDTOS = new ArrayList<>();
//        for (Director director : directors) {
//            directorResponseDTOS.add(directorMapper.mapDirectorToDirectorResponseDTO(director));
//        }
//        return directorResponseDTOS;
//    }

// @Transactional(readOnly = true)
//    @Override
//    public List<DirectorResponseDTO> getAll() {
//        return directorRepository.findAll().stream().map(d -> directorMapper.mapDirectorToDirectorResponseDTO(d)).toList();
//    }

    @Transactional(readOnly = true)
    @Override
    public List<DirectorResponseDTO> getAll() {
        return directorRepository.findAll().stream().map(directorMapper::mapDirectorToDirectorResponseDTO).toList();
    }

    @Transactional
    @Override
    public DirectorResponseDTO createDirector(DirectorCreateRequestDTO directorCreateRequestDTO) {
        Director director = directorMapper.mapDirectorCreateRequestToDirector(directorCreateRequestDTO);
        directorRepository.save(director);
        return directorMapper.mapDirectorToDirectorResponseDTO(director);
    }

    @Transactional
    @Override
    public DirectorResponseDTO updateDirector(Long id, DirectorUpdateRequestDTO directorUpdateRequestDTO) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Director not found"));
        if (directorUpdateRequestDTO.getFirstName() != null) {
            director.setFirstName(directorUpdateRequestDTO.getFirstName());
        }

        if (directorUpdateRequestDTO.getLastName() != null) {
            director.setLastName(directorUpdateRequestDTO.getLastName());
        }
        directorRepository.save(director);
        return directorMapper.mapDirectorToDirectorResponseDTO(director);
    }

    @Transactional
    @Override
    public void deleteDirectorById(Long id) {
        directorRepository.deleteById(id);
    }
}
