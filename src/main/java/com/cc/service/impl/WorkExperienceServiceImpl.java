package com.cc.service.impl;

import com.cc.service.WorkExperienceService;
import com.cc.domain.WorkExperience;
import com.cc.repository.WorkExperienceRepository;
import com.cc.service.dto.WorkExperienceDTO;
import com.cc.service.mapper.WorkExperienceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WorkExperience}.
 */
@Service
@Transactional
public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final Logger log = LoggerFactory.getLogger(WorkExperienceServiceImpl.class);

    private final WorkExperienceRepository workExperienceRepository;

    private final WorkExperienceMapper workExperienceMapper;

    public WorkExperienceServiceImpl(WorkExperienceRepository workExperienceRepository, WorkExperienceMapper workExperienceMapper) {
        this.workExperienceRepository = workExperienceRepository;
        this.workExperienceMapper = workExperienceMapper;
    }

    /**
     * Save a workExperience.
     *
     * @param workExperienceDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public WorkExperienceDTO save(WorkExperienceDTO workExperienceDTO) {
        log.debug("Request to save WorkExperience : {}", workExperienceDTO);
        WorkExperience workExperience = workExperienceMapper.toEntity(workExperienceDTO);
        workExperience = workExperienceRepository.save(workExperience);
        return workExperienceMapper.toDto(workExperience);
    }

    /**
     * Get all the workExperiences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<WorkExperienceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkExperiences");
        return workExperienceRepository.findAll(pageable)
            .map(workExperienceMapper::toDto);
    }


    /**
     * Get one workExperience by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<WorkExperienceDTO> findOne(Long id) {
        log.debug("Request to get WorkExperience : {}", id);
        return workExperienceRepository.findById(id)
            .map(workExperienceMapper::toDto);
    }

    /**
     * Delete the workExperience by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkExperience : {}", id);
        workExperienceRepository.deleteById(id);
    }
}
