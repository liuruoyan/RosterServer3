package com.cc.service.impl;

import com.cc.service.EnumEmpTaxerTypeService;
import com.cc.domain.EnumEmpTaxerType;
import com.cc.repository.EnumEmpTaxerTypeRepository;
import com.cc.service.dto.EnumEmpTaxerTypeDTO;
import com.cc.service.mapper.EnumEmpTaxerTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumEmpTaxerType}.
 */
@Service
@Transactional
public class EnumEmpTaxerTypeServiceImpl implements EnumEmpTaxerTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTaxerTypeServiceImpl.class);

    private final EnumEmpTaxerTypeRepository enumEmpTaxerTypeRepository;

    private final EnumEmpTaxerTypeMapper enumEmpTaxerTypeMapper;

    public EnumEmpTaxerTypeServiceImpl(EnumEmpTaxerTypeRepository enumEmpTaxerTypeRepository, EnumEmpTaxerTypeMapper enumEmpTaxerTypeMapper) {
        this.enumEmpTaxerTypeRepository = enumEmpTaxerTypeRepository;
        this.enumEmpTaxerTypeMapper = enumEmpTaxerTypeMapper;
    }

    /**
     * Save a enumEmpTaxerType.
     *
     * @param enumEmpTaxerTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumEmpTaxerTypeDTO save(EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO) {
        log.debug("Request to save EnumEmpTaxerType : {}", enumEmpTaxerTypeDTO);
        EnumEmpTaxerType enumEmpTaxerType = enumEmpTaxerTypeMapper.toEntity(enumEmpTaxerTypeDTO);
        enumEmpTaxerType = enumEmpTaxerTypeRepository.save(enumEmpTaxerType);
        return enumEmpTaxerTypeMapper.toDto(enumEmpTaxerType);
    }

    /**
     * Get all the enumEmpTaxerTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumEmpTaxerTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumEmpTaxerTypes");
        return enumEmpTaxerTypeRepository.findAll(pageable)
            .map(enumEmpTaxerTypeMapper::toDto);
    }


    /**
     * Get one enumEmpTaxerType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumEmpTaxerTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumEmpTaxerType : {}", id);
        return enumEmpTaxerTypeRepository.findById(id)
            .map(enumEmpTaxerTypeMapper::toDto);
    }

    /**
     * Delete the enumEmpTaxerType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumEmpTaxerType : {}", id);
        enumEmpTaxerTypeRepository.deleteById(id);
    }
}
