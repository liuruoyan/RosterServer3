package com.cc.service.impl;

import com.cc.service.EnumEducationService;
import com.cc.domain.EnumEducation;
import com.cc.repository.EnumEducationRepository;
import com.cc.service.dto.EnumEducationDTO;
import com.cc.service.mapper.EnumEducationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumEducation}.
 */
@Service
@Transactional
public class EnumEducationServiceImpl implements EnumEducationService {

    private final Logger log = LoggerFactory.getLogger(EnumEducationServiceImpl.class);

    private final EnumEducationRepository enumEducationRepository;

    private final EnumEducationMapper enumEducationMapper;

    public EnumEducationServiceImpl(EnumEducationRepository enumEducationRepository, EnumEducationMapper enumEducationMapper) {
        this.enumEducationRepository = enumEducationRepository;
        this.enumEducationMapper = enumEducationMapper;
    }

    /**
     * Save a enumEducation.
     *
     * @param enumEducationDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumEducationDTO save(EnumEducationDTO enumEducationDTO) {
        log.debug("Request to save EnumEducation : {}", enumEducationDTO);
        EnumEducation enumEducation = enumEducationMapper.toEntity(enumEducationDTO);
        enumEducation = enumEducationRepository.save(enumEducation);
        return enumEducationMapper.toDto(enumEducation);
    }

    /**
     * Get all the enumEducations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumEducationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumEducations");
        return enumEducationRepository.findAll(pageable)
            .map(enumEducationMapper::toDto);
    }


    /**
     * Get one enumEducation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumEducationDTO> findOne(Long id) {
        log.debug("Request to get EnumEducation : {}", id);
        return enumEducationRepository.findById(id)
            .map(enumEducationMapper::toDto);
    }

    /**
     * Delete the enumEducation by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumEducation : {}", id);
        enumEducationRepository.deleteById(id);
    }
}
