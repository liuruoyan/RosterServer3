package com.cc.service;

import com.cc.service.dto.DimissionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.Dimission}.
 */
public interface DimissionService {

    /**
     * Save a dimission.
     *
     * @param dimissionDTO the entity to save.
     * @return the persisted entity.
     */
    DimissionDTO save(DimissionDTO dimissionDTO);

    /**
     * Get all the dimissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DimissionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dimission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DimissionDTO> findOne(Long id);

    /**
     * Delete the "id" dimission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
