package com.cc.service.impl;

import com.cc.service.EnumGenderService;
import com.cc.domain.EnumGender;
import com.cc.repository.EnumGenderRepository;
import com.cc.service.dto.EnumGenderDTO;
import com.cc.service.mapper.EnumGenderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumGender}.
 */
@Service
@Transactional
public class EnumGenderServiceImpl implements EnumGenderService {

    private final Logger log = LoggerFactory.getLogger(EnumGenderServiceImpl.class);

    private final EnumGenderRepository enumGenderRepository;

    private final EnumGenderMapper enumGenderMapper;

    public EnumGenderServiceImpl(EnumGenderRepository enumGenderRepository, EnumGenderMapper enumGenderMapper) {
        this.enumGenderRepository = enumGenderRepository;
        this.enumGenderMapper = enumGenderMapper;
    }

    /**
     * Save a enumGender.
     *
     * @param enumGenderDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumGenderDTO save(EnumGenderDTO enumGenderDTO) {
        log.debug("Request to save EnumGender : {}", enumGenderDTO);
        EnumGender enumGender = enumGenderMapper.toEntity(enumGenderDTO);
        enumGender = enumGenderRepository.save(enumGender);
        return enumGenderMapper.toDto(enumGender);
    }

    /**
     * Get all the enumGenders.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumGenderDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumGenders");
        return enumGenderRepository.findAll(pageable)
            .map(enumGenderMapper::toDto);
    }


    /**
     * Get one enumGender by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumGenderDTO> findOne(Long id) {
        log.debug("Request to get EnumGender : {}", id);
        return enumGenderRepository.findById(id)
            .map(enumGenderMapper::toDto);
    }

    /**
     * Delete the enumGender by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumGender : {}", id);
        enumGenderRepository.deleteById(id);
    }
}
