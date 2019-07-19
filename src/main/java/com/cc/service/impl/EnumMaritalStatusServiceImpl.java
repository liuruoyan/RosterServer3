package com.cc.service.impl;

import com.cc.service.EnumMaritalStatusService;
import com.cc.domain.EnumMaritalStatus;
import com.cc.repository.EnumMaritalStatusRepository;
import com.cc.service.dto.EnumMaritalStatusDTO;
import com.cc.service.mapper.EnumMaritalStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumMaritalStatus}.
 */
@Service
@Transactional
public class EnumMaritalStatusServiceImpl implements EnumMaritalStatusService {

    private final Logger log = LoggerFactory.getLogger(EnumMaritalStatusServiceImpl.class);

    private final EnumMaritalStatusRepository enumMaritalStatusRepository;

    private final EnumMaritalStatusMapper enumMaritalStatusMapper;

    public EnumMaritalStatusServiceImpl(EnumMaritalStatusRepository enumMaritalStatusRepository, EnumMaritalStatusMapper enumMaritalStatusMapper) {
        this.enumMaritalStatusRepository = enumMaritalStatusRepository;
        this.enumMaritalStatusMapper = enumMaritalStatusMapper;
    }

    /**
     * Save a enumMaritalStatus.
     *
     * @param enumMaritalStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumMaritalStatusDTO save(EnumMaritalStatusDTO enumMaritalStatusDTO) {
        log.debug("Request to save EnumMaritalStatus : {}", enumMaritalStatusDTO);
        EnumMaritalStatus enumMaritalStatus = enumMaritalStatusMapper.toEntity(enumMaritalStatusDTO);
        enumMaritalStatus = enumMaritalStatusRepository.save(enumMaritalStatus);
        return enumMaritalStatusMapper.toDto(enumMaritalStatus);
    }

    /**
     * Get all the enumMaritalStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumMaritalStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumMaritalStatuses");
        return enumMaritalStatusRepository.findAll(pageable)
            .map(enumMaritalStatusMapper::toDto);
    }


    /**
     * Get one enumMaritalStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumMaritalStatusDTO> findOne(Long id) {
        log.debug("Request to get EnumMaritalStatus : {}", id);
        return enumMaritalStatusRepository.findById(id)
            .map(enumMaritalStatusMapper::toDto);
    }

    /**
     * Delete the enumMaritalStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumMaritalStatus : {}", id);
        enumMaritalStatusRepository.deleteById(id);
    }
}
