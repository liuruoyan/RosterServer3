package com.cc.service.impl;

import com.cc.service.EnumSsPaySchemeService;
import com.cc.domain.EnumSsPayScheme;
import com.cc.repository.EnumSsPaySchemeRepository;
import com.cc.service.dto.EnumSsPaySchemeDTO;
import com.cc.service.mapper.EnumSsPaySchemeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumSsPayScheme}.
 */
@Service
@Transactional
public class EnumSsPaySchemeServiceImpl implements EnumSsPaySchemeService {

    private final Logger log = LoggerFactory.getLogger(EnumSsPaySchemeServiceImpl.class);

    private final EnumSsPaySchemeRepository enumSsPaySchemeRepository;

    private final EnumSsPaySchemeMapper enumSsPaySchemeMapper;

    public EnumSsPaySchemeServiceImpl(EnumSsPaySchemeRepository enumSsPaySchemeRepository, EnumSsPaySchemeMapper enumSsPaySchemeMapper) {
        this.enumSsPaySchemeRepository = enumSsPaySchemeRepository;
        this.enumSsPaySchemeMapper = enumSsPaySchemeMapper;
    }

    /**
     * Save a enumSsPayScheme.
     *
     * @param enumSsPaySchemeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumSsPaySchemeDTO save(EnumSsPaySchemeDTO enumSsPaySchemeDTO) {
        log.debug("Request to save EnumSsPayScheme : {}", enumSsPaySchemeDTO);
        EnumSsPayScheme enumSsPayScheme = enumSsPaySchemeMapper.toEntity(enumSsPaySchemeDTO);
        enumSsPayScheme = enumSsPaySchemeRepository.save(enumSsPayScheme);
        return enumSsPaySchemeMapper.toDto(enumSsPayScheme);
    }

    /**
     * Get all the enumSsPaySchemes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumSsPaySchemeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumSsPaySchemes");
        return enumSsPaySchemeRepository.findAll(pageable)
            .map(enumSsPaySchemeMapper::toDto);
    }


    /**
     * Get one enumSsPayScheme by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumSsPaySchemeDTO> findOne(Long id) {
        log.debug("Request to get EnumSsPayScheme : {}", id);
        return enumSsPaySchemeRepository.findById(id)
            .map(enumSsPaySchemeMapper::toDto);
    }

    /**
     * Delete the enumSsPayScheme by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumSsPayScheme : {}", id);
        enumSsPaySchemeRepository.deleteById(id);
    }
}
