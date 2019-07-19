package com.cc.service.impl;

import com.cc.service.EnumDimissionTypeService;
import com.cc.domain.EnumDimissionType;
import com.cc.repository.EnumDimissionTypeRepository;
import com.cc.service.dto.EnumDimissionTypeDTO;
import com.cc.service.mapper.EnumDimissionTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumDimissionType}.
 */
@Service
@Transactional
public class EnumDimissionTypeServiceImpl implements EnumDimissionTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumDimissionTypeServiceImpl.class);

    private final EnumDimissionTypeRepository enumDimissionTypeRepository;

    private final EnumDimissionTypeMapper enumDimissionTypeMapper;

    public EnumDimissionTypeServiceImpl(EnumDimissionTypeRepository enumDimissionTypeRepository, EnumDimissionTypeMapper enumDimissionTypeMapper) {
        this.enumDimissionTypeRepository = enumDimissionTypeRepository;
        this.enumDimissionTypeMapper = enumDimissionTypeMapper;
    }

    /**
     * Save a enumDimissionType.
     *
     * @param enumDimissionTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumDimissionTypeDTO save(EnumDimissionTypeDTO enumDimissionTypeDTO) {
        log.debug("Request to save EnumDimissionType : {}", enumDimissionTypeDTO);
        EnumDimissionType enumDimissionType = enumDimissionTypeMapper.toEntity(enumDimissionTypeDTO);
        enumDimissionType = enumDimissionTypeRepository.save(enumDimissionType);
        return enumDimissionTypeMapper.toDto(enumDimissionType);
    }

    /**
     * Get all the enumDimissionTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumDimissionTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumDimissionTypes");
        return enumDimissionTypeRepository.findAll(pageable)
            .map(enumDimissionTypeMapper::toDto);
    }


    /**
     * Get one enumDimissionType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumDimissionTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumDimissionType : {}", id);
        return enumDimissionTypeRepository.findById(id)
            .map(enumDimissionTypeMapper::toDto);
    }

    /**
     * Delete the enumDimissionType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumDimissionType : {}", id);
        enumDimissionTypeRepository.deleteById(id);
    }
}
