package com.cc.service;

import com.cc.service.dto.SocialSecurityBenefitsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.SocialSecurityBenefits}.
 */
public interface SocialSecurityBenefitsService {

    /**
     * Save a socialSecurityBenefits.
     *
     * @param socialSecurityBenefitsDTO the entity to save.
     * @return the persisted entity.
     */
    SocialSecurityBenefitsDTO save(SocialSecurityBenefitsDTO socialSecurityBenefitsDTO);

    /**
     * Get all the socialSecurityBenefits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SocialSecurityBenefitsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" socialSecurityBenefits.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SocialSecurityBenefitsDTO> findOne(Long id);

    /**
     * Delete the "id" socialSecurityBenefits.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
