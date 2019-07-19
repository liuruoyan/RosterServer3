package com.cc.service;

import com.cc.service.dto.EnumEmpStatusDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumEmpStatus}.
 */
public interface EnumEmpStatusService {

    /**
     * Save a enumEmpStatus.
     *
     * @param enumEmpStatusDTO the entity to save.
     * @return the persisted entity.
     */
    EnumEmpStatusDTO save(EnumEmpStatusDTO enumEmpStatusDTO);

    /**
     * Get all the enumEmpStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumEmpStatusDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumEmpStatus.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumEmpStatusDTO> findOne(Long id);

    /**
     * Delete the "id" enumEmpStatus.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
