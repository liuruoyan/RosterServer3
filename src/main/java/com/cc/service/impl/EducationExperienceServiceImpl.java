package com.cc.service.impl;

import com.cc.service.EducationExperienceService;
import com.cc.domain.EducationExperience;
import com.cc.repository.EducationExperienceRepository;
import com.cc.service.dto.EducationExperienceDTO;
import com.cc.service.mapper.EducationExperienceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EducationExperience}.
 */
@Service
@Transactional
public class EducationExperienceServiceImpl implements EducationExperienceService {

    private final Logger log = LoggerFactory.getLogger(EducationExperienceServiceImpl.class);

    private final EducationExperienceRepository educationExperienceRepository;

    private final EducationExperienceMapper educationExperienceMapper;

    public EducationExperienceServiceImpl(EducationExperienceRepository educationExperienceRepository, EducationExperienceMapper educationExperienceMapper) {
        this.educationExperienceRepository = educationExperienceRepository;
        this.educationExperienceMapper = educationExperienceMapper;
    }

    /**
     * Save a educationExperience.
     *
     * @param educationExperienceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EducationExperienceDTO save(EducationExperienceDTO educationExperienceDTO) {
        log.debug("Request to save EducationExperience : {}", educationExperienceDTO);
        EducationExperience educationExperience = educationExperienceMapper.toEntity(educationExperienceDTO);
        educationExperience = educationExperienceRepository.save(educationExperience);
        return educationExperienceMapper.toDto(educationExperience);
    }

    /**
     * Get all the educationExperiences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationExperienceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationExperiences");
        return educationExperienceRepository.findAll(pageable)
            .map(educationExperienceMapper::toDto);
    }


    /**
     * Get one educationExperience by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationExperienceDTO> findOne(Long id) {
        log.debug("Request to get EducationExperience : {}", id);
        return educationExperienceRepository.findById(id)
            .map(educationExperienceMapper::toDto);
    }

    /**
     * Delete the educationExperience by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationExperience : {}", id);
        educationExperienceRepository.deleteById(id);
    }
}
