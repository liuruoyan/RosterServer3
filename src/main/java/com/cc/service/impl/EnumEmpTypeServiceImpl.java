package com.cc.service.impl;

import com.cc.service.EnumEmpTypeService;
import com.cc.domain.EnumEmpType;
import com.cc.repository.EnumEmpTypeRepository;
import com.cc.service.dto.EnumEmpTypeDTO;
import com.cc.service.mapper.EnumEmpTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumEmpType}.
 */
@Service
@Transactional
public class EnumEmpTypeServiceImpl implements EnumEmpTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTypeServiceImpl.class);

    private final EnumEmpTypeRepository enumEmpTypeRepository;

    private final EnumEmpTypeMapper enumEmpTypeMapper;

    public EnumEmpTypeServiceImpl(EnumEmpTypeRepository enumEmpTypeRepository, EnumEmpTypeMapper enumEmpTypeMapper) {
        this.enumEmpTypeRepository = enumEmpTypeRepository;
        this.enumEmpTypeMapper = enumEmpTypeMapper;
    }

    /**
     * Save a enumEmpType.
     *
     * @param enumEmpTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumEmpTypeDTO save(EnumEmpTypeDTO enumEmpTypeDTO) {
        log.debug("Request to save EnumEmpType : {}", enumEmpTypeDTO);
        EnumEmpType enumEmpType = enumEmpTypeMapper.toEntity(enumEmpTypeDTO);
        enumEmpType = enumEmpTypeRepository.save(enumEmpType);
        return enumEmpTypeMapper.toDto(enumEmpType);
    }

    /**
     * Get all the enumEmpTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumEmpTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumEmpTypes");
        return enumEmpTypeRepository.findAll(pageable)
            .map(enumEmpTypeMapper::toDto);
    }


    /**
     * Get one enumEmpType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumEmpTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumEmpType : {}", id);
        return enumEmpTypeRepository.findById(id)
            .map(enumEmpTypeMapper::toDto);
    }

    /**
     * Delete the enumEmpType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumEmpType : {}", id);
        enumEmpTypeRepository.deleteById(id);
    }
}
