package com.cc.service;

import com.cc.service.dto.EnumPoliticsStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumPoliticsStatus}.
 */
public interface EnumPoliticsStatusService {

    /**
     * Save a enumPoliticsStatus.
     *
     * @param enumPoliticsStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EnumPoliticsStatusDTO save(EnumPoliticsStatusDTO enumPoliticsStatusDTO);

    /**
     * Get all the enumPoliticsStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumPoliticsStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumPoliticsStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumPoliticsStatusDTO> findOne(Long id);

    /**
     * Delete the "id" enumPoliticsStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
