package com.cc.service;

import com.cc.service.dto.EnumSsPaySchemeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumSsPayScheme}.
 */
public interface EnumSsPaySchemeService {

    /**
     * Save a enumSsPayScheme.
     *
     * @param enumSsPaySchemeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumSsPaySchemeDTO save(EnumSsPaySchemeDTO enumSsPaySchemeDTO);

    /**
     * Get all the enumSsPaySchemes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumSsPaySchemeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumSsPayScheme.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumSsPaySchemeDTO> findOne(Long id);

    /**
     * Delete the "id" enumSsPayScheme.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
