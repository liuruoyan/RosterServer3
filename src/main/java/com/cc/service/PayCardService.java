package com.cc.service;

import com.cc.service.dto.PayCardDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.PayCard}.
 */
public interface PayCardService {

    /**
     * Save a payCard.
     *
     * @param payCardDTO the entity to save.
     * @return the persisted entity.
     */
    PayCardDTO save(PayCardDTO payCardDTO);

    /**
     * Get all the payCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayCardDTO> findAll(Pageable pageable);


    /**
     * Get the "id" payCard.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayCardDTO> findOne(Long id);

    /**
     * Delete the "id" payCard.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
