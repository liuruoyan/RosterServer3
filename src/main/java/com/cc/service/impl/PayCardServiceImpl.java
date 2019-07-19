package com.cc.service.impl;

import com.cc.service.PayCardService;
import com.cc.domain.PayCard;
import com.cc.repository.PayCardRepository;
import com.cc.service.dto.PayCardDTO;
import com.cc.service.mapper.PayCardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PayCard}.
 */
@Service
@Transactional
public class PayCardServiceImpl implements PayCardService {

    private final Logger log = LoggerFactory.getLogger(PayCardServiceImpl.class);

    private final PayCardRepository payCardRepository;

    private final PayCardMapper payCardMapper;

    public PayCardServiceImpl(PayCardRepository payCardRepository, PayCardMapper payCardMapper) {
        this.payCardRepository = payCardRepository;
        this.payCardMapper = payCardMapper;
    }

    /**
     * Save a payCard.
     *
     * @param payCardDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PayCardDTO save(PayCardDTO payCardDTO) {
        log.debug("Request to save PayCard : {}", payCardDTO);
        PayCard payCard = payCardMapper.toEntity(payCardDTO);
        payCard = payCardRepository.save(payCard);
        return payCardMapper.toDto(payCard);
    }

    /**
     * Get all the payCards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PayCardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PayCards");
        return payCardRepository.findAll(pageable)
            .map(payCardMapper::toDto);
    }


    /**
     * Get one payCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PayCardDTO> findOne(Long id) {
        log.debug("Request to get PayCard : {}", id);
        return payCardRepository.findById(id)
            .map(payCardMapper::toDto);
    }

    /**
     * Delete the payCard by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PayCard : {}", id);
        payCardRepository.deleteById(id);
    }
}
