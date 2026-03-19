package com.ezequielmartin.horrorverse_api.service;

import com.ezequielmartin.horrorverse_api.dto.GenreCreateRequestDTO;
import com.ezequielmartin.horrorverse_api.dto.GenreResponseDTO;
import com.ezequielmartin.horrorverse_api.dto.GenreUpdateRequestDTO;
import com.ezequielmartin.horrorverse_api.mapper.GenreMapper;
import com.ezequielmartin.horrorverse_api.model.Genre;
import com.ezequielmartin.horrorverse_api.repository.IGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.util.StringUtils.capitalize;

@Service
@RequiredArgsConstructor
public class GenreService implements IGenreService {

    private final IGenreRepository iGenreRepository;
    private final GenreMapper genreMapper;

    @Transactional(readOnly = true)
    @Override
    public GenreResponseDTO findById(Long id) {
        Genre genre = iGenreRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Genre not found"));
        return genreMapper.mapGenreToGenreResponseDTO(genre);
    }

    @Transactional(readOnly = true)
    @Override
    public List<GenreResponseDTO> findAll() {
        return iGenreRepository.findAll().stream().map(g -> genreMapper.mapGenreToGenreResponseDTO(g)).toList();
    }

    @Transactional
    @Override
    public GenreResponseDTO createGenre(GenreCreateRequestDTO genreCreateRequestDTO) {

        String normalizedName = genreCreateRequestDTO.getName().trim().toLowerCase();
        if(iGenreRepository.existsByNameIgnoreCase(normalizedName)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Genre already exists");
        }

        Genre genre = genreMapper.mapGenreCreateRequestDTOToGenre(genreCreateRequestDTO);
        genre.setName(capitalize(normalizedName));
        iGenreRepository.save(genre);

        return genreMapper.mapGenreToGenreResponseDTO(genre);
    }

    @Transactional
    @Override
    public GenreResponseDTO updateGenre(Long id, GenreUpdateRequestDTO genreUpdateRequestDTO) {
        Genre genre = iGenreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Genre not found"));

        if (genreUpdateRequestDTO.getId() != null) {
            genre.setId(genreUpdateRequestDTO.getId());
        }
        if (genreUpdateRequestDTO.getName() != null) {
            genre.setName(genreUpdateRequestDTO.getName());
        }

        iGenreRepository.save(genre);

        return genreMapper.mapGenreToGenreResponseDTO(genre);
    }

    @Transactional
    @Override
    public void deleteGenre(Long id) {
        iGenreRepository.deleteById(id);
    }
}
