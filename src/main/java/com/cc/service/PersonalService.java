package com.cc.service;

import com.cc.service.dto.PersonalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.Personal}.
 */
public interface PersonalService {

    /**
     * Save a personal.
     *
     * @param personalDTO the entity to save.
     * @return the persisted entity.
     */
    PersonalDTO save(PersonalDTO personalDTO);

    /**
     * Get all the personals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PersonalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" personal.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PersonalDTO> findOne(Long id);

    /**
     * Delete the "id" personal.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
