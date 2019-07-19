package com.cc.service.impl;

import com.cc.service.EnumPfPaySchemeService;
import com.cc.domain.EnumPfPayScheme;
import com.cc.repository.EnumPfPaySchemeRepository;
import com.cc.service.dto.EnumPfPaySchemeDTO;
import com.cc.service.mapper.EnumPfPaySchemeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumPfPayScheme}.
 */
@Service
@Transactional
public class EnumPfPaySchemeServiceImpl implements EnumPfPaySchemeService {

    private final Logger log = LoggerFactory.getLogger(EnumPfPaySchemeServiceImpl.class);

    private final EnumPfPaySchemeRepository enumPfPaySchemeRepository;

    private final EnumPfPaySchemeMapper enumPfPaySchemeMapper;

    public EnumPfPaySchemeServiceImpl(EnumPfPaySchemeRepository enumPfPaySchemeRepository, EnumPfPaySchemeMapper enumPfPaySchemeMapper) {
        this.enumPfPaySchemeRepository = enumPfPaySchemeRepository;
        this.enumPfPaySchemeMapper = enumPfPaySchemeMapper;
    }

    /**
     * Save a enumPfPayScheme.
     *
     * @param enumPfPaySchemeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumPfPaySchemeDTO save(EnumPfPaySchemeDTO enumPfPaySchemeDTO) {
        log.debug("Request to save EnumPfPayScheme : {}", enumPfPaySchemeDTO);
        EnumPfPayScheme enumPfPayScheme = enumPfPaySchemeMapper.toEntity(enumPfPaySchemeDTO);
        enumPfPayScheme = enumPfPaySchemeRepository.save(enumPfPayScheme);
        return enumPfPaySchemeMapper.toDto(enumPfPayScheme);
    }

    /**
     * Get all the enumPfPaySchemes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumPfPaySchemeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumPfPaySchemes");
        return enumPfPaySchemeRepository.findAll(pageable)
            .map(enumPfPaySchemeMapper::toDto);
    }


    /**
     * Get one enumPfPayScheme by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumPfPaySchemeDTO> findOne(Long id) {
        log.debug("Request to get EnumPfPayScheme : {}", id);
        return enumPfPaySchemeRepository.findById(id)
            .map(enumPfPaySchemeMapper::toDto);
    }

    /**
     * Delete the enumPfPayScheme by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumPfPayScheme : {}", id);
        enumPfPaySchemeRepository.deleteById(id);
    }
}
