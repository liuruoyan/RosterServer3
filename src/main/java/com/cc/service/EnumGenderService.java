package com.cc.service;

import com.cc.service.dto.EnumGenderDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EnumGender}.
 */
public interface EnumGenderService {

    /**
     * Save a enumGender.
     *
     * @param enumGenderDTO the entity to save.
     * @return the persisted entity.
     */
    EnumGenderDTO save(EnumGenderDTO enumGenderDTO);

    /**
     * Get all the enumGenders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EnumGenderDTO> findAll(Pageable pageable);


    /**
     * Get the "id" enumGender.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EnumGenderDTO> findOne(Long id);

    /**
     * Delete the "id" enumGender.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
