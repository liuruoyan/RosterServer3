package com.cc.service;

import com.cc.service.dto.EnumPfStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumPfStatus}.
 */
public interface EnumPfStatusService {

    /**
     * Save a enumPfStatus.
     *
     * @param enumPfStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EnumPfStatusDTO save(EnumPfStatusDTO enumPfStatusDTO);

    /**
     * Get all the enumPfStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumPfStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumPfStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumPfStatusDTO> findOne(Long id);

    /**
     * Delete the "id" enumPfStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
