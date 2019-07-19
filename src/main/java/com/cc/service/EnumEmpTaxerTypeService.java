package com.cc.service;

import com.cc.service.dto.EnumEmpTaxerTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumEmpTaxerType}.
 */
public interface EnumEmpTaxerTypeService {

    /**
     * Save a enumEmpTaxerType.
     *
     * @param enumEmpTaxerTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumEmpTaxerTypeDTO save(EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO);

    /**
     * Get all the enumEmpTaxerTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumEmpTaxerTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumEmpTaxerType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumEmpTaxerTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumEmpTaxerType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
