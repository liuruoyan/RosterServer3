package com.cc.service;

import com.cc.service.dto.EnumDimissionTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumDimissionType}.
 */
public interface EnumDimissionTypeService {

    /**
     * Save a enumDimissionType.
     *
     * @param enumDimissionTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumDimissionTypeDTO save(EnumDimissionTypeDTO enumDimissionTypeDTO);

    /**
     * Get all the enumDimissionTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumDimissionTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumDimissionType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumDimissionTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumDimissionType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
