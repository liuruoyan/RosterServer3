package com.cc.service.impl;

import com.cc.service.DirectSupervisorService;
import com.cc.domain.DirectSupervisor;
import com.cc.repository.DirectSupervisorRepository;
import com.cc.service.dto.DirectSupervisorDTO;
import com.cc.service.mapper.DirectSupervisorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DirectSupervisor}.
 */
@Service
@Transactional
public class DirectSupervisorServiceImpl implements DirectSupervisorService {

    private final Logger log = LoggerFactory.getLogger(DirectSupervisorServiceImpl.class);

    private final DirectSupervisorRepository directSupervisorRepository;

    private final DirectSupervisorMapper directSupervisorMapper;

    public DirectSupervisorServiceImpl(DirectSupervisorRepository directSupervisorRepository, DirectSupervisorMapper directSupervisorMapper) {
        this.directSupervisorRepository = directSupervisorRepository;
        this.directSupervisorMapper = directSupervisorMapper;
    }

    /**
     * Save a directSupervisor.
     *
     * @param directSupervisorDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DirectSupervisorDTO save(DirectSupervisorDTO directSupervisorDTO) {
        log.debug("Request to save DirectSupervisor : {}", directSupervisorDTO);
        DirectSupervisor directSupervisor = directSupervisorMapper.toEntity(directSupervisorDTO);
        directSupervisor = directSupervisorRepository.save(directSupervisor);
        return directSupervisorMapper.toDto(directSupervisor);
    }

    /**
     * Get all the directSupervisors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DirectSupervisorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DirectSupervisors");
        return directSupervisorRepository.findAll(pageable)
            .map(directSupervisorMapper::toDto);
    }


    /**
     * Get one directSupervisor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DirectSupervisorDTO> findOne(Long id) {
        log.debug("Request to get DirectSupervisor : {}", id);
        return directSupervisorRepository.findById(id)
            .map(directSupervisorMapper::toDto);
    }

    /**
     * Delete the directSupervisor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DirectSupervisor : {}", id);
        directSupervisorRepository.deleteById(id);
    }
}
