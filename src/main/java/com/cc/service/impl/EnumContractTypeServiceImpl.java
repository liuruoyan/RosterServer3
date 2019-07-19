package com.cc.service.impl;

import com.cc.service.EnumContractTypeService;
import com.cc.domain.EnumContractType;
import com.cc.repository.EnumContractTypeRepository;
import com.cc.service.dto.EnumContractTypeDTO;
import com.cc.service.mapper.EnumContractTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumContractType}.
 */
@Service
@Transactional
public class EnumContractTypeServiceImpl implements EnumContractTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumContractTypeServiceImpl.class);

    private final EnumContractTypeRepository enumContractTypeRepository;

    private final EnumContractTypeMapper enumContractTypeMapper;

    public EnumContractTypeServiceImpl(EnumContractTypeRepository enumContractTypeRepository, EnumContractTypeMapper enumContractTypeMapper) {
        this.enumContractTypeRepository = enumContractTypeRepository;
        this.enumContractTypeMapper = enumContractTypeMapper;
    }

    /**
     * Save a enumContractType.
     *
     * @param enumContractTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumContractTypeDTO save(EnumContractTypeDTO enumContractTypeDTO) {
        log.debug("Request to save EnumContractType : {}", enumContractTypeDTO);
        EnumContractType enumContractType = enumContractTypeMapper.toEntity(enumContractTypeDTO);
        enumContractType = enumContractTypeRepository.save(enumContractType);
        return enumContractTypeMapper.toDto(enumContractType);
    }

    /**
     * Get all the enumContractTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumContractTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumContractTypes");
        return enumContractTypeRepository.findAll(pageable)
            .map(enumContractTypeMapper::toDto);
    }


    /**
     * Get one enumContractType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumContractTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumContractType : {}", id);
        return enumContractTypeRepository.findById(id)
            .map(enumContractTypeMapper::toDto);
    }

    /**
     * Delete the enumContractType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumContractType : {}", id);
        enumContractTypeRepository.deleteById(id);
    }
}
