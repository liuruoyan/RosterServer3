package com.cc.service;

import com.cc.service.dto.EnumContractTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumContractType}.
 */
public interface EnumContractTypeService {

    /**
     * Save a enumContractType.
     *
     * @param enumContractTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumContractTypeDTO save(EnumContractTypeDTO enumContractTypeDTO);

    /**
     * Get all the enumContractTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumContractTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumContractType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumContractTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumContractType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
