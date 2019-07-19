package com.cc.service;

import com.cc.service.dto.EnumAccountTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumAccountType}.
 */
public interface EnumAccountTypeService {

    /**
     * Save a enumAccountType.
     *
     * @param enumAccountTypeDTO the entity to save.
     * @return the persisted entity.
     */
    EnumAccountTypeDTO save(EnumAccountTypeDTO enumAccountTypeDTO);

    /**
     * Get all the enumAccountTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumAccountTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumAccountType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumAccountTypeDTO> findOne(Long id);

    /**
     * Delete the "id" enumAccountType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
