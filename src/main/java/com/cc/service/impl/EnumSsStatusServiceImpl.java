package com.cc.service.impl;

import com.cc.service.EnumSsStatusService;
import com.cc.domain.EnumSsStatus;
import com.cc.repository.EnumSsStatusRepository;
import com.cc.service.dto.EnumSsStatusDTO;
import com.cc.service.mapper.EnumSsStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumSsStatus}.
 */
@Service
@Transactional
public class EnumSsStatusServiceImpl implements EnumSsStatusService {

    private final Logger log = LoggerFactory.getLogger(EnumSsStatusServiceImpl.class);

    private final EnumSsStatusRepository enumSsStatusRepository;

    private final EnumSsStatusMapper enumSsStatusMapper;

    public EnumSsStatusServiceImpl(EnumSsStatusRepository enumSsStatusRepository, EnumSsStatusMapper enumSsStatusMapper) {
        this.enumSsStatusRepository = enumSsStatusRepository;
        this.enumSsStatusMapper = enumSsStatusMapper;
    }

    /**
     * Save a enumSsStatus.
     *
     * @param enumSsStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumSsStatusDTO save(EnumSsStatusDTO enumSsStatusDTO) {
        log.debug("Request to save EnumSsStatus : {}", enumSsStatusDTO);
        EnumSsStatus enumSsStatus = enumSsStatusMapper.toEntity(enumSsStatusDTO);
        enumSsStatus = enumSsStatusRepository.save(enumSsStatus);
        return enumSsStatusMapper.toDto(enumSsStatus);
    }

    /**
     * Get all the enumSsStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumSsStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumSsStatuses");
        return enumSsStatusRepository.findAll(pageable)
            .map(enumSsStatusMapper::toDto);
    }


    /**
     * Get one enumSsStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumSsStatusDTO> findOne(Long id) {
        log.debug("Request to get EnumSsStatus : {}", id);
        return enumSsStatusRepository.findById(id)
            .map(enumSsStatusMapper::toDto);
    }

    /**
     * Delete the enumSsStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumSsStatus : {}", id);
        enumSsStatusRepository.deleteById(id);
    }
}
