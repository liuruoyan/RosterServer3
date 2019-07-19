package com.cc.service;

import com.cc.service.dto.DirectSupervisorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.DirectSupervisor}.
 */
public interface DirectSupervisorService {

    /**
     * Save a directSupervisor.
     *
     * @param directSupervisorDTO the entity to save.
     * @return the persisted entity.
     */
    DirectSupervisorDTO save(DirectSupervisorDTO directSupervisorDTO);

    /**
     * Get all the directSupervisors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DirectSupervisorDTO> findAll(Pageable pageable);


    /**
     * Get the "id" directSupervisor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DirectSupervisorDTO> findOne(Long id);

    /**
     * Delete the "id" directSupervisor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
