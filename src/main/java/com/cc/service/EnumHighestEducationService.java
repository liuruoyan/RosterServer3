package com.cc.service;

import com.cc.service.dto.EnumHighestEducationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumHighestEducation}.
 */
public interface EnumHighestEducationService {

    /**
     * Save a enumHighestEducation.
     *
     * @param enumHighestEducationDTO the entity to save.
     * @return the persisted entity.
     */
    EnumHighestEducationDTO save(EnumHighestEducationDTO enumHighestEducationDTO);

    /**
     * Get all the enumHighestEducations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumHighestEducationDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumHighestEducation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumHighestEducationDTO> findOne(Long id);

    /**
     * Delete the "id" enumHighestEducation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
