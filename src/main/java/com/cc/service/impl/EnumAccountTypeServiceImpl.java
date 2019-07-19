package com.cc.service.impl;

import com.cc.service.EnumAccountTypeService;
import com.cc.domain.EnumAccountType;
import com.cc.repository.EnumAccountTypeRepository;
import com.cc.service.dto.EnumAccountTypeDTO;
import com.cc.service.mapper.EnumAccountTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumAccountType}.
 */
@Service
@Transactional
public class EnumAccountTypeServiceImpl implements EnumAccountTypeService {

    private final Logger log = LoggerFactory.getLogger(EnumAccountTypeServiceImpl.class);

    private final EnumAccountTypeRepository enumAccountTypeRepository;

    private final EnumAccountTypeMapper enumAccountTypeMapper;

    public EnumAccountTypeServiceImpl(EnumAccountTypeRepository enumAccountTypeRepository, EnumAccountTypeMapper enumAccountTypeMapper) {
        this.enumAccountTypeRepository = enumAccountTypeRepository;
        this.enumAccountTypeMapper = enumAccountTypeMapper;
    }

    /**
     * Save a enumAccountType.
     *
     * @param enumAccountTypeDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumAccountTypeDTO save(EnumAccountTypeDTO enumAccountTypeDTO) {
        log.debug("Request to save EnumAccountType : {}", enumAccountTypeDTO);
        EnumAccountType enumAccountType = enumAccountTypeMapper.toEntity(enumAccountTypeDTO);
        enumAccountType = enumAccountTypeRepository.save(enumAccountType);
        return enumAccountTypeMapper.toDto(enumAccountType);
    }

    /**
     * Get all the enumAccountTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumAccountTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumAccountTypes");
        return enumAccountTypeRepository.findAll(pageable)
            .map(enumAccountTypeMapper::toDto);
    }


    /**
     * Get one enumAccountType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumAccountTypeDTO> findOne(Long id) {
        log.debug("Request to get EnumAccountType : {}", id);
        return enumAccountTypeRepository.findById(id)
            .map(enumAccountTypeMapper::toDto);
    }

    /**
     * Delete the enumAccountType by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumAccountType : {}", id);
        enumAccountTypeRepository.deleteById(id);
    }
}
