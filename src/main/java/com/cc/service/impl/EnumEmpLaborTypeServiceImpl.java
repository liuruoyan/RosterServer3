package com.cc.service.impl;

import com.cc.service.EnumEmpLaborTypeService;
import com.cc.domain.EnumEmpLaborType;
import com.cc.repository.EnumEmpLaborTypeRepository;
import com.cc.service.dto.EnumEmpLaborTypeDTO;
import com.cc.service.mapper.EnumEmpLaborTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumEmpLaborType}.
 */
@Service
@Transactional
public class EnumEmpLaborTypeServiceImpl implements EnumEmpLaborTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumEmpLaborTypeServiceImpl.class);

    private final EnumEmpLaborTypeRepository enumEmpLaborTypeRepository;

    private final EnumEmpLaborTypeMapper enumEmpLaborTypeMapper;

    public EnumEmpLaborTypeServiceImpl(EnumEmpLaborTypeRepository enumEmpLaborTypeRepository, EnumEmpLaborTypeMapper enumEmpLaborTypeMapper) {
        this.enumEmpLaborTypeRepository = enumEmpLaborTypeRepository;
        this.enumEmpLaborTypeMapper = enumEmpLaborTypeMapper;
    }

    /**
     * Save a enumEmpLaborType.
     *
     * @param enumEmpLaborTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumEmpLaborTypeDTO save(EnumEmpLaborTypeDTO enumEmpLaborTypeDTO) {
        log.debug("Request to save EnumEmpLaborType : {}", enumEmpLaborTypeDTO);
        EnumEmpLaborType enumEmpLaborType = enumEmpLaborTypeMapper.toEntity(enumEmpLaborTypeDTO);
        enumEmpLaborType = enumEmpLaborTypeRepository.save(enumEmpLaborType);
        return enumEmpLaborTypeMapper.toDto(enumEmpLaborType);
    }

    /**
     * Get all the enumEmpLaborTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumEmpLaborTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumEmpLaborTypes");
        return enumEmpLaborTypeRepository.findAll(pageable)
            .map(enumEmpLaborTypeMapper::toDto);
    }


    /**
     * Get one enumEmpLaborType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumEmpLaborTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumEmpLaborType : {}", id);
        return enumEmpLaborTypeRepository.findById(id)
            .map(enumEmpLaborTypeMapper::toDto);
    }

    /**
     * Delete the enumEmpLaborType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumEmpLaborType : {}", id);
        enumEmpLaborTypeRepository.deleteById(id);
    }
}
