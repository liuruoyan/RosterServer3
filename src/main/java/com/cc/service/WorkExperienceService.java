package com.cc.service;

import com.cc.service.dto.WorkExperienceDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.WorkExperience}.
 */
public interface WorkExperienceService {

    /**
     * Save a workExperience.
     *
     * @param workExperienceDTO the entity to save.
     * @return the persisted entity.
     */
    WorkExperienceDTO save(WorkExperienceDTO workExperienceDTO);

    /**
     * Get all the workExperiences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<WorkExperienceDTO> findAll(Pageable pageable);


    /**
     * Get the "id" workExperience.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<WorkExperienceDTO> findOne(Long id);

    /**
     * Delete the "id" workExperience.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
