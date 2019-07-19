package com.cc.service;

import com.cc.service.dto.AdditionalPostDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.cc.domain.AdditionalPost}.
 */
public interface AdditionalPostService {

    /**
     * Save a additionalPost.
     *
     * @param additionalPostDTO the entity to save.
     * @return the persisted entity.
     */
    AdditionalPostDTO save(AdditionalPostDTO additionalPostDTO);

    /**
     * Get all the additionalPosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdditionalPostDTO> findAll(Pageable pageable);


    /**
     * Get the "id" additionalPost.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdditionalPostDTO> findOne(Long id);

    /**
     * Delete the "id" additionalPost.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
