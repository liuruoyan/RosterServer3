package com.cc.service;

import com.cc.service.dto.EnumEmpLaborTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumEmpLaborType}.
 */
public interface EnumEmpLaborTypeService {

    /**
     * Save a enumEmpLaborType.
     *
     * @param enumEmpLaborTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumEmpLaborTypeDTO save(EnumEmpLaborTypeDTO enumEmpLaborTypeDTO);

    /**
     * Get all the enumEmpLaborTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumEmpLaborTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumEmpLaborType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumEmpLaborTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumEmpLaborType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
