package com.cc.service.impl;

import com.cc.service.DimissionService;
import com.cc.domain.Dimission;
import com.cc.repository.DimissionRepository;
import com.cc.service.dto.DimissionDTO;
import com.cc.service.mapper.DimissionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Dimission}.
 */
@Service
@Transactional
public class DimissionServiceImpl implements DimissionService {

    private final Logger log = LoggerFactory.getLogger(DimissionServiceImpl.class);

    private final DimissionRepository dimissionRepository;

    private final DimissionMapper dimissionMapper;

    public DimissionServiceImpl(DimissionRepository dimissionRepository, DimissionMapper dimissionMapper) {
        this.dimissionRepository = dimissionRepository;
        this.dimissionMapper = dimissionMapper;
    }

    /**
     * Save a dimission.
     *
     * @param dimissionDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DimissionDTO save(DimissionDTO dimissionDTO) {
        log.debug("Request to save Dimission : {}", dimissionDTO);
        Dimission dimission = dimissionMapper.toEntity(dimissionDTO);
        dimission = dimissionRepository.save(dimission);
        return dimissionMapper.toDto(dimission);
    }

    /**
     * Get all the dimissions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DimissionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Dimissions");
        return dimissionRepository.findAll(pageable)
            .map(dimissionMapper::toDto);
    }


    /**
     * Get one dimission by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DimissionDTO> findOne(Long id) {
        log.debug("Request to get Dimission : {}", id);
        return dimissionRepository.findById(id)
            .map(dimissionMapper::toDto);
    }

    /**
     * Delete the dimission by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Dimission : {}", id);
        dimissionRepository.deleteById(id);
    }
}
