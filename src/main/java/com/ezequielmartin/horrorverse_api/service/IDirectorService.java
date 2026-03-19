package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.DirectorCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.DirectorResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.DirectorUpdateRequestDTO;

import java.util.List;

public interface IDirectorService {
    public DirectorResponseDTO getDirectorById(Long id);
    public List<DirectorResponseDTO> getAll();
    public DirectorResponseDTO createDirector(DirectorCreateRequestDTO directorCreateRequestDTO);
    public DirectorResponseDTO updateDirector(Long id, DirectorUpdateRequestDTO directorUpdateRequestDTO);
    public void deleteDirectorById(Long id);
}
