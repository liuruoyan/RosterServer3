package com.cc.service;

import com.cc.service.dto.EnumEducationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumEducation}.
 */
public interface EnumEducationService {

    /**
     * Save a enumEducation.
     *
     * @param enumEducationDTO the entity to save.
     * @return the persisted entity.
     */
    EnumEducationDTO save(EnumEducationDTO enumEducationDTO);

    /**
     * Get all the enumEducations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumEducationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumEducation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumEducationDTO> findOne(Long id);

    /**
     * Delete the "id" enumEducation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
