package com.cc.service;

import com.cc.service.dto.EnumIdTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumIdType}.
 */
public interface EnumIdTypeService {

    /**
     * Save a enumIdType.
     *
     * @param enumIdTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumIdTypeDTO save(EnumIdTypeDTO enumIdTypeDTO);

    /**
     * Get all the enumIdTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumIdTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumIdType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumIdTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumIdType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
