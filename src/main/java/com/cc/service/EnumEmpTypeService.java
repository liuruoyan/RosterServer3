package com.cc.service;

import com.cc.service.dto.EnumEmpTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumEmpType}.
 */
public interface EnumEmpTypeService {

    /**
     * Save a enumEmpType.
     *
     * @param enumEmpTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumEmpTypeDTO save(EnumEmpTypeDTO enumEmpTypeDTO);

    /**
     * Get all the enumEmpTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumEmpTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumEmpType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumEmpTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumEmpType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
