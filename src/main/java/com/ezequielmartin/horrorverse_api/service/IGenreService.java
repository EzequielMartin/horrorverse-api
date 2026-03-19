package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.GenreCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.GenreResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.GenreUpdateRequestDTO;

import java.util.List;

public interface IGenreService {
    public GenreResponseDTO findById(Long id);
    public List<GenreResponseDTO> findAll();
    public GenreResponseDTO createGenre(GenreCreateRequestDTO genreCreateRequestDTO);
    public GenreResponseDTO updateGenre(Long id, GenreUpdateRequestDTO genreUpdateRequestDTO);
    public void deleteGenre(Long id);
}
