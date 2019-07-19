package com.cc.service;

import com.cc.service.dto.EnumSsStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumSsStatus}.
 */
public interface EnumSsStatusService {

    /**
     * Save a enumSsStatus.
     *
     * @param enumSsStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EnumSsStatusDTO save(EnumSsStatusDTO enumSsStatusDTO);

    /**
     * Get all the enumSsStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumSsStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumSsStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumSsStatusDTO> findOne(Long id);

    /**
     * Delete the "id" enumSsStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
