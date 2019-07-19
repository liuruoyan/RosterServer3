package com.cc.service.impl;

import com.cc.service.EnumIdTypeService;
import com.cc.domain.EnumIdType;
import com.cc.repository.EnumIdTypeRepository;
import com.cc.service.dto.EnumIdTypeDTO;
import com.cc.service.mapper.EnumIdTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumIdType}.
 */
@Service
@Transactional
public class EnumIdTypeServiceImpl implements EnumIdTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumIdTypeServiceImpl.class);

    private final EnumIdTypeRepository enumIdTypeRepository;

    private final EnumIdTypeMapper enumIdTypeMapper;

    public EnumIdTypeServiceImpl(EnumIdTypeRepository enumIdTypeRepository, EnumIdTypeMapper enumIdTypeMapper) {
        this.enumIdTypeRepository = enumIdTypeRepository;
        this.enumIdTypeMapper = enumIdTypeMapper;
    }

    /**
     * Save a enumIdType.
     *
     * @param enumIdTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumIdTypeDTO save(EnumIdTypeDTO enumIdTypeDTO) {
        log.debug("Request to save EnumIdType : {}", enumIdTypeDTO);
        EnumIdType enumIdType = enumIdTypeMapper.toEntity(enumIdTypeDTO);
        enumIdType = enumIdTypeRepository.save(enumIdType);
        return enumIdTypeMapper.toDto(enumIdType);
    }

    /**
     * Get all the enumIdTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumIdTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumIdTypes");
        return enumIdTypeRepository.findAll(pageable)
            .map(enumIdTypeMapper::toDto);
    }


    /**
     * Get one enumIdType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumIdTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumIdType : {}", id);
        return enumIdTypeRepository.findById(id)
            .map(enumIdTypeMapper::toDto);
    }

    /**
     * Delete the enumIdType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumIdType : {}", id);
        enumIdTypeRepository.deleteById(id);
    }
}
