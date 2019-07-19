package com.cc.service.impl;

import com.cc.service.EnumHighestEducationService;
import com.cc.domain.EnumHighestEducation;
import com.cc.repository.EnumHighestEducationRepository;
import com.cc.service.dto.EnumHighestEducationDTO;
import com.cc.service.mapper.EnumHighestEducationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumHighestEducation}.
 */
@Service
@Transactional
public class EnumHighestEducationServiceImpl implements EnumHighestEducationService {

    private final Logger log = LoggerFactory.getLogger(EnumHighestEducationServiceImpl.class);

    private final EnumHighestEducationRepository enumHighestEducationRepository;

    private final EnumHighestEducationMapper enumHighestEducationMapper;

    public EnumHighestEducationServiceImpl(EnumHighestEducationRepository enumHighestEducationRepository, EnumHighestEducationMapper enumHighestEducationMapper) {
        this.enumHighestEducationRepository = enumHighestEducationRepository;
        this.enumHighestEducationMapper = enumHighestEducationMapper;
    }

    /**
     * Save a enumHighestEducation.
     *
     * @param enumHighestEducationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumHighestEducationDTO save(EnumHighestEducationDTO enumHighestEducationDTO) {
        log.debug("Request to save EnumHighestEducation : {}", enumHighestEducationDTO);
        EnumHighestEducation enumHighestEducation = enumHighestEducationMapper.toEntity(enumHighestEducationDTO);
        enumHighestEducation = enumHighestEducationRepository.save(enumHighestEducation);
        return enumHighestEducationMapper.toDto(enumHighestEducation);
    }

    /**
     * Get all the enumHighestEducations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumHighestEducationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumHighestEducations");
        return enumHighestEducationRepository.findAll(pageable)
            .map(enumHighestEducationMapper::toDto);
    }


    /**
     * Get one enumHighestEducation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumHighestEducationDTO> findOne(Long id) {
        log.debug("Request to get EnumHighestEducation : {}", id);
        return enumHighestEducationRepository.findById(id)
            .map(enumHighestEducationMapper::toDto);
    }

    /**
     * Delete the enumHighestEducation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumHighestEducation : {}", id);
        enumHighestEducationRepository.deleteById(id);
    }
}
