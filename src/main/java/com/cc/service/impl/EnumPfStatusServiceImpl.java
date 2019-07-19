package com.cc.service.impl;

import com.cc.service.EnumPfStatusService;
import com.cc.domain.EnumPfStatus;
import com.cc.repository.EnumPfStatusRepository;
import com.cc.service.dto.EnumPfStatusDTO;
import com.cc.service.mapper.EnumPfStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumPfStatus}.
 */
@Service
@Transactional
public class EnumPfStatusServiceImpl implements EnumPfStatusService {

    private final Logger log = LoggerFactory.getLogger(EnumPfStatusServiceImpl.class);

    private final EnumPfStatusRepository enumPfStatusRepository;

    private final EnumPfStatusMapper enumPfStatusMapper;

    public EnumPfStatusServiceImpl(EnumPfStatusRepository enumPfStatusRepository, EnumPfStatusMapper enumPfStatusMapper) {
        this.enumPfStatusRepository = enumPfStatusRepository;
        this.enumPfStatusMapper = enumPfStatusMapper;
    }

    /**
     * Save a enumPfStatus.
     *
     * @param enumPfStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumPfStatusDTO save(EnumPfStatusDTO enumPfStatusDTO) {
        log.debug("Request to save EnumPfStatus : {}", enumPfStatusDTO);
        EnumPfStatus enumPfStatus = enumPfStatusMapper.toEntity(enumPfStatusDTO);
        enumPfStatus = enumPfStatusRepository.save(enumPfStatus);
        return enumPfStatusMapper.toDto(enumPfStatus);
    }

    /**
     * Get all the enumPfStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumPfStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumPfStatuses");
        return enumPfStatusRepository.findAll(pageable)
            .map(enumPfStatusMapper::toDto);
    }


    /**
     * Get one enumPfStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumPfStatusDTO> findOne(Long id) {
        log.debug("Request to get EnumPfStatus : {}", id);
        return enumPfStatusRepository.findById(id)
            .map(enumPfStatusMapper::toDto);
    }

    /**
     * Delete the enumPfStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumPfStatus : {}", id);
        enumPfStatusRepository.deleteById(id);
    }
}
