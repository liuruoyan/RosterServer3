package com.cc.service.impl;

import com.cc.service.EnumEmpStatusService;
import com.cc.domain.EnumEmpStatus;
import com.cc.repository.EnumEmpStatusRepository;
import com.cc.service.dto.EnumEmpStatusDTO;
import com.cc.service.mapper.EnumEmpStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumEmpStatus}.
 */
@Service
@Transactional
public class EnumEmpStatusServiceImpl implements EnumEmpStatusService {

    private final Logger log = LoggerFactory.getLogger(EnumEmpStatusServiceImpl.class);

    private final EnumEmpStatusRepository enumEmpStatusRepository;

    private final EnumEmpStatusMapper enumEmpStatusMapper;

    public EnumEmpStatusServiceImpl(EnumEmpStatusRepository enumEmpStatusRepository, EnumEmpStatusMapper enumEmpStatusMapper) {
        this.enumEmpStatusRepository = enumEmpStatusRepository;
        this.enumEmpStatusMapper = enumEmpStatusMapper;
    }

    /**
     * Save a enumEmpStatus.
     *
     * @param enumEmpStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumEmpStatusDTO save(EnumEmpStatusDTO enumEmpStatusDTO) {
        log.debug("Request to save EnumEmpStatus : {}", enumEmpStatusDTO);
        EnumEmpStatus enumEmpStatus = enumEmpStatusMapper.toEntity(enumEmpStatusDTO);
        enumEmpStatus = enumEmpStatusRepository.save(enumEmpStatus);
        return enumEmpStatusMapper.toDto(enumEmpStatus);
    }

    /**
     * Get all the enumEmpStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumEmpStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumEmpStatuses");
        return enumEmpStatusRepository.findAll(pageable)
            .map(enumEmpStatusMapper::toDto);
    }


    /**
     * Get one enumEmpStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumEmpStatusDTO> findOne(Long id) {
        log.debug("Request to get EnumEmpStatus : {}", id);
        return enumEmpStatusRepository.findById(id)
            .map(enumEmpStatusMapper::toDto);
    }

    /**
     * Delete the enumEmpStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumEmpStatus : {}", id);
        enumEmpStatusRepository.deleteById(id);
    }
}
