package com.cc.service;

import com.cc.service.dto.EnumEmpTaxAreaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumEmpTaxArea}.
 */
public interface EnumEmpTaxAreaService {

    /**
     * Save a enumEmpTaxArea.
     *
     * @param enumEmpTaxAreaDTO the entity to save.
     * @return the persisted entity.
     */
    EnumEmpTaxAreaDTO save(EnumEmpTaxAreaDTO enumEmpTaxAreaDTO);

    /**
     * Get all the enumEmpTaxAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumEmpTaxAreaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumEmpTaxArea.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumEmpTaxAreaDTO> findOne(Long id);

    /**
     * Delete the "id" enumEmpTaxArea.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
