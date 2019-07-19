package com.cc.service;

import com.cc.service.dto.EducationExperienceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.EducationExperience}.
 */
public interface EducationExperienceService {

    /**
     * Save a educationExperience.
     *
     * @param educationExperienceDTO the entity to save.
     * @return the persisted entity.
     */
    EducationExperienceDTO save(EducationExperienceDTO educationExperienceDTO);

    /**
     * Get all the educationExperiences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EducationExperienceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" educationExperience.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EducationExperienceDTO> findOne(Long id);

    /**
     * Delete the "id" educationExperience.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
