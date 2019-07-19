package com.cc.service.impl;

import com.cc.service.EnumEmpTaxAreaService;
import com.cc.domain.EnumEmpTaxArea;
import com.cc.repository.EnumEmpTaxAreaRepository;
import com.cc.service.dto.EnumEmpTaxAreaDTO;
import com.cc.service.mapper.EnumEmpTaxAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumEmpTaxArea}.
 */
@Service
@Transactional
public class EnumEmpTaxAreaServiceImpl implements EnumEmpTaxAreaService {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTaxAreaServiceImpl.class);

    private final EnumEmpTaxAreaRepository enumEmpTaxAreaRepository;

    private final EnumEmpTaxAreaMapper enumEmpTaxAreaMapper;

    public EnumEmpTaxAreaServiceImpl(EnumEmpTaxAreaRepository enumEmpTaxAreaRepository, EnumEmpTaxAreaMapper enumEmpTaxAreaMapper) {
        this.enumEmpTaxAreaRepository = enumEmpTaxAreaRepository;
        this.enumEmpTaxAreaMapper = enumEmpTaxAreaMapper;
    }

    /**
     * Save a enumEmpTaxArea.
     *
     * @param enumEmpTaxAreaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumEmpTaxAreaDTO save(EnumEmpTaxAreaDTO enumEmpTaxAreaDTO) {
        log.debug("Request to save EnumEmpTaxArea : {}", enumEmpTaxAreaDTO);
        EnumEmpTaxArea enumEmpTaxArea = enumEmpTaxAreaMapper.toEntity(enumEmpTaxAreaDTO);
        enumEmpTaxArea = enumEmpTaxAreaRepository.save(enumEmpTaxArea);
        return enumEmpTaxAreaMapper.toDto(enumEmpTaxArea);
    }

    /**
     * Get all the enumEmpTaxAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumEmpTaxAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumEmpTaxAreas");
        return enumEmpTaxAreaRepository.findAll(pageable)
            .map(enumEmpTaxAreaMapper::toDto);
    }


    /**
     * Get one enumEmpTaxArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumEmpTaxAreaDTO> findOne(Long id) {
        log.debug("Request to get EnumEmpTaxArea : {}", id);
        return enumEmpTaxAreaRepository.findById(id)
            .map(enumEmpTaxAreaMapper::toDto);
    }

    /**
     * Delete the enumEmpTaxArea by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumEmpTaxArea : {}", id);
        enumEmpTaxAreaRepository.deleteById(id);
    }
}
