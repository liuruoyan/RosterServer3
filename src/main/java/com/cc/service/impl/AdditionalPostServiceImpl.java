package com.cc.service.impl;

import com.cc.service.AdditionalPostService;
import com.cc.domain.AdditionalPost;
import com.cc.repository.AdditionalPostRepository;
import com.cc.service.dto.AdditionalPostDTO;
import com.cc.service.mapper.AdditionalPostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdditionalPost}.
 */
@Service
@Transactional
public class AdditionalPostServiceImpl implements AdditionalPostService {

    private final Logger log = LoggerFactory.getLogger(AdditionalPostServiceImpl.class);

    private final AdditionalPostRepository additionalPostRepository;

    private final AdditionalPostMapper additionalPostMapper;

    public AdditionalPostServiceImpl(AdditionalPostRepository additionalPostRepository, AdditionalPostMapper additionalPostMapper) {
        this.additionalPostRepository = additionalPostRepository;
        this.additionalPostMapper = additionalPostMapper;
    }

    /**
     * Save a additionalPost.
     *
     * @param additionalPostDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AdditionalPostDTO save(AdditionalPostDTO additionalPostDTO) {
        log.debug("Request to save AdditionalPost : {}", additionalPostDTO);
        AdditionalPost additionalPost = additionalPostMapper.toEntity(additionalPostDTO);
        additionalPost = additionalPostRepository.save(additionalPost);
        return additionalPostMapper.toDto(additionalPost);
    }

    /**
     * Get all the additionalPosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AdditionalPostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdditionalPosts");
        return additionalPostRepository.findAll(pageable)
            .map(additionalPostMapper::toDto);
    }


    /**
     * Get one additionalPost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdditionalPostDTO> findOne(Long id) {
        log.debug("Request to get AdditionalPost : {}", id);
        return additionalPostRepository.findById(id)
            .map(additionalPostMapper::toDto);
    }

    /**
     * Delete the additionalPost by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdditionalPost : {}", id);
        additionalPostRepository.deleteById(id);
    }
}
