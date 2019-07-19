package com.cc.service;

import com.cc.service.dto.EnumPfPaySchemeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumPfPayScheme}.
 */
public interface EnumPfPaySchemeService {

    /**
     * Save a enumPfPayScheme.
     *
     * @param enumPfPaySchemeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumPfPaySchemeDTO save(EnumPfPaySchemeDTO enumPfPaySchemeDTO);

    /**
     * Get all the enumPfPaySchemes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumPfPaySchemeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumPfPayScheme.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumPfPaySchemeDTO> findOne(Long id);

    /**
     * Delete the "id" enumPfPayScheme.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
