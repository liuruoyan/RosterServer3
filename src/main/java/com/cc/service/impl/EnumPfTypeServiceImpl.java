package com.cc.service.impl;

import com.cc.service.EnumPfTypeService;
import com.cc.domain.EnumPfType;
import com.cc.repository.EnumPfTypeRepository;
import com.cc.service.dto.EnumPfTypeDTO;
import com.cc.service.mapper.EnumPfTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumPfType}.
 */
@Service
@Transactional
public class EnumPfTypeServiceImpl implements EnumPfTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumPfTypeServiceImpl.class);

    private final EnumPfTypeRepository enumPfTypeRepository;

    private final EnumPfTypeMapper enumPfTypeMapper;

    public EnumPfTypeServiceImpl(EnumPfTypeRepository enumPfTypeRepository, EnumPfTypeMapper enumPfTypeMapper) {
        this.enumPfTypeRepository = enumPfTypeRepository;
        this.enumPfTypeMapper = enumPfTypeMapper;
    }

    /**
     * Save a enumPfType.
     *
     * @param enumPfTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumPfTypeDTO save(EnumPfTypeDTO enumPfTypeDTO) {
        log.debug("Request to save EnumPfType : {}", enumPfTypeDTO);
        EnumPfType enumPfType = enumPfTypeMapper.toEntity(enumPfTypeDTO);
        enumPfType = enumPfTypeRepository.save(enumPfType);
        return enumPfTypeMapper.toDto(enumPfType);
    }

    /**
     * Get all the enumPfTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumPfTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumPfTypes");
        return enumPfTypeRepository.findAll(pageable)
            .map(enumPfTypeMapper::toDto);
    }


    /**
     * Get one enumPfType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumPfTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumPfType : {}", id);
        return enumPfTypeRepository.findById(id)
            .map(enumPfTypeMapper::toDto);
    }

    /**
     * Delete the enumPfType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumPfType : {}", id);
        enumPfTypeRepository.deleteById(id);
    }
}
