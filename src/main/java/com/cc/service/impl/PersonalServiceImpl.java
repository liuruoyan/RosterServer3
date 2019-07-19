package com.cc.service.impl;

import com.cc.service.PersonalService;
import com.cc.domain.Personal;
import com.cc.repository.PersonalRepository;
import com.cc.service.dto.PersonalDTO;
import com.cc.service.mapper.PersonalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Personal}.
 */
@Service
@Transactional
public class PersonalServiceImpl implements PersonalService {

    private final Logger log = LoggerFactory.getLogger(PersonalServiceImpl.class);

    private final PersonalRepository personalRepository;

    private final PersonalMapper personalMapper;

    public PersonalServiceImpl(PersonalRepository personalRepository, PersonalMapper personalMapper) {
        this.personalRepository = personalRepository;
        this.personalMapper = personalMapper;
    }

    /**
     * Save a personal.
     *
     * @param personalDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PersonalDTO save(PersonalDTO personalDTO) {
        log.debug("Request to save Personal : {}", personalDTO);
        Personal personal = personalMapper.toEntity(personalDTO);
        personal = personalRepository.save(personal);
        return personalMapper.toDto(personal);
    }

    /**
     * Get all the personals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PersonalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Personals");
        return personalRepository.findAll(pageable)
            .map(personalMapper::toDto);
    }


    /**
     * Get one personal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalDTO> findOne(Long id) {
        log.debug("Request to get Personal : {}", id);
        return personalRepository.findById(id)
            .map(personalMapper::toDto);
    }

    /**
     * Delete the personal by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Personal : {}", id);
        personalRepository.deleteById(id);
    }
}
