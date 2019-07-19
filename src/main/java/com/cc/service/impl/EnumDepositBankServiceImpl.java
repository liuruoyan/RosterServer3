package com.cc.service.impl;

import com.cc.service.EnumDepositBankService;
import com.cc.domain.EnumDepositBank;
import com.cc.repository.EnumDepositBankRepository;
import com.cc.service.dto.EnumDepositBankDTO;
import com.cc.service.mapper.EnumDepositBankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link EnumDepositBank}.
 */
@Service
@Transactional
public class EnumDepositBankServiceImpl implements EnumDepositBankService {

    private final Logger log = LoggerFactory.getLogger(EnumDepositBankServiceImpl.class);

    private final EnumDepositBankRepository enumDepositBankRepository;

    private final EnumDepositBankMapper enumDepositBankMapper;

    public EnumDepositBankServiceImpl(EnumDepositBankRepository enumDepositBankRepository, EnumDepositBankMapper enumDepositBankMapper) {
        this.enumDepositBankRepository = enumDepositBankRepository;
        this.enumDepositBankMapper = enumDepositBankMapper;
    }

    /**
     * Save a enumDepositBank.
     *
     * @param enumDepositBankDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EnumDepositBankDTO save(EnumDepositBankDTO enumDepositBankDTO) {
        log.debug("Request to save EnumDepositBank : {}", enumDepositBankDTO);
        EnumDepositBank enumDepositBank = enumDepositBankMapper.toEntity(enumDepositBankDTO);
        enumDepositBank = enumDepositBankRepository.save(enumDepositBank);
        return enumDepositBankMapper.toDto(enumDepositBank);
    }

    /**
     * Get all the enumDepositBanks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EnumDepositBankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EnumDepositBanks");
        return enumDepositBankRepository.findAll(pageable)
            .map(enumDepositBankMapper::toDto);
    }


    /**
     * Get one enumDepositBank by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EnumDepositBankDTO> findOne(Long id) {
        log.debug("Request to get EnumDepositBank : {}", id);
        return enumDepositBankRepository.findById(id)
            .map(enumDepositBankMapper::toDto);
    }

    /**
     * Delete the enumDepositBank by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EnumDepositBank : {}", id);
        enumDepositBankRepository.deleteById(id);
    }
}
