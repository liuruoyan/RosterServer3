package com.cc.service;

import com.cc.service.dto.EnumPfTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumPfType}.
 */
public interface EnumPfTypeService {

    /**
     * Save a enumPfType.
     *
     * @param enumPfTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumPfTypeDTO save(EnumPfTypeDTO enumPfTypeDTO);

    /**
     * Get all the enumPfTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumPfTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumPfType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumPfTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumPfType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
