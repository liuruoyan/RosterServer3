package com.cc.service;

import com.cc.service.dto.EnumMaritalStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumMaritalStatus}.
 */
public interface EnumMaritalStatusService {

    /**
     * Save a enumMaritalStatus.
     *
     * @param enumMaritalStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EnumMaritalStatusDTO save(EnumMaritalStatusDTO enumMaritalStatusDTO);

    /**
     * Get all the enumMaritalStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumMaritalStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumMaritalStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumMaritalStatusDTO> findOne(Long id);

    /**
     * Delete the "id" enumMaritalStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
