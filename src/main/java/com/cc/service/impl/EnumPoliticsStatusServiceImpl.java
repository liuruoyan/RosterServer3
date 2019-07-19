package com.cc.service.impl;

import com.cc.service.EnumPoliticsStatusService;
import com.cc.domain.EnumPoliticsStatus;
import com.cc.repository.EnumPoliticsStatusRepository;
import com.cc.service.dto.EnumPoliticsStatusDTO;
import com.cc.service.mapper.EnumPoliticsStatusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumPoliticsStatus}.
 */
@Service
@Transactional
public class EnumPoliticsStatusServiceImpl implements EnumPoliticsStatusService {

    private final Logger log = LoggerFactory.getLogger(EnumPoliticsStatusServiceImpl.class);

    private final EnumPoliticsStatusRepository enumPoliticsStatusRepository;

    private final EnumPoliticsStatusMapper enumPoliticsStatusMapper;

    public EnumPoliticsStatusServiceImpl(EnumPoliticsStatusRepository enumPoliticsStatusRepository, EnumPoliticsStatusMapper enumPoliticsStatusMapper) {
        this.enumPoliticsStatusRepository = enumPoliticsStatusRepository;
        this.enumPoliticsStatusMapper = enumPoliticsStatusMapper;
    }

    /**
     * Save a enumPoliticsStatus.
     *
     * @param enumPoliticsStatusDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumPoliticsStatusDTO save(EnumPoliticsStatusDTO enumPoliticsStatusDTO) {
        log.debug("Request to save EnumPoliticsStatus : {}", enumPoliticsStatusDTO);
        EnumPoliticsStatus enumPoliticsStatus = enumPoliticsStatusMapper.toEntity(enumPoliticsStatusDTO);
        enumPoliticsStatus = enumPoliticsStatusRepository.save(enumPoliticsStatus);
        return enumPoliticsStatusMapper.toDto(enumPoliticsStatus);
    }

    /**
     * Get all the enumPoliticsStatuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumPoliticsStatusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumPoliticsStatuses");
        return enumPoliticsStatusRepository.findAll(pageable)
            .map(enumPoliticsStatusMapper::toDto);
    }


    /**
     * Get one enumPoliticsStatus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumPoliticsStatusDTO> findOne(Long id) {
        log.debug("Request to get EnumPoliticsStatus : {}", id);
        return enumPoliticsStatusRepository.findById(id)
            .map(enumPoliticsStatusMapper::toDto);
    }

    /**
     * Delete the enumPoliticsStatus by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumPoliticsStatus : {}", id);
        enumPoliticsStatusRepository.deleteById(id);
    }
}
