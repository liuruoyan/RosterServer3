package com.cc.service.impl;

import com.cc.service.SocialSecurityBenefitsService;
import com.cc.domain.SocialSecurityBenefits;
import com.cc.repository.SocialSecurityBenefitsRepository;
import com.cc.service.dto.SocialSecurityBenefitsDTO;
import com.cc.service.mapper.SocialSecurityBenefitsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link SocialSecurityBenefits}.
 */
@Service
@Transactional
public class SocialSecurityBenefitsServiceImpl implements SocialSecurityBenefitsService {

    private final Logger log = LoggerFactory.getLogger(SocialSecurityBenefitsServiceImpl.class);

    private final SocialSecurityBenefitsRepository socialSecurityBenefitsRepository;

    private final SocialSecurityBenefitsMapper socialSecurityBenefitsMapper;

    public SocialSecurityBenefitsServiceImpl(SocialSecurityBenefitsRepository socialSecurityBenefitsRepository, SocialSecurityBenefitsMapper socialSecurityBenefitsMapper) {
        this.socialSecurityBenefitsRepository = socialSecurityBenefitsRepository;
        this.socialSecurityBenefitsMapper = socialSecurityBenefitsMapper;
    }

    /**
     * Save a socialSecurityBenefits.
     *
     * @param socialSecurityBenefitsDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SocialSecurityBenefitsDTO save(SocialSecurityBenefitsDTO socialSecurityBenefitsDTO) {
        log.debug("Request to save SocialSecurityBenefits : {}", socialSecurityBenefitsDTO);
        SocialSecurityBenefits socialSecurityBenefits = socialSecurityBenefitsMapper.toEntity(socialSecurityBenefitsDTO);
        socialSecurityBenefits = socialSecurityBenefitsRepository.save(socialSecurityBenefits);
        return socialSecurityBenefitsMapper.toDto(socialSecurityBenefits);
    }

    /**
     * Get all the socialSecurityBenefits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<SocialSecurityBenefitsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SocialSecurityBenefits");
        return socialSecurityBenefitsRepository.findAll(pageable)
            .map(socialSecurityBenefitsMapper::toDto);
    }


    /**
     * Get one socialSecurityBenefits by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SocialSecurityBenefitsDTO> findOne(Long id) {
        log.debug("Request to get SocialSecurityBenefits : {}", id);
        return socialSecurityBenefitsRepository.findById(id)
            .map(socialSecurityBenefitsMapper::toDto);
    }

    /**
     * Delete the socialSecurityBenefits by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SocialSecurityBenefits : {}", id);
        socialSecurityBenefitsRepository.deleteById(id);
    }
}
