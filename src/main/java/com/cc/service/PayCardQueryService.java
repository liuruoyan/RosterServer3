package com.cc.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.cc.domain.PayCard;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.PayCardRepository;
import com.cc.service.dto.PayCardCriteria;
import com.cc.service.dto.PayCardDTO;
import com.cc.service.mapper.PayCardMapper;

/**
 * Service for executing complex queries for {@link PayCard} entities in the database.
 * The main input is a {@link PayCardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PayCardDTO} or a {@link Page} of {@link PayCardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PayCardQueryService extends QueryService<PayCard> {

    private final Logger log = LoggerFactory.getLogger(PayCardQueryService.class);

    private final PayCardRepository payCardRepository;

    private final PayCardMapper payCardMapper;

    public PayCardQueryService(PayCardRepository payCardRepository, PayCardMapper payCardMapper) {
        this.payCardRepository = payCardRepository;
        this.payCardMapper = payCardMapper;
    }

    /**
     * Return a {@link List} of {@link PayCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PayCardDTO> findByCriteria(PayCardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PayCard> specification = createSpecification(criteria);
        return payCardMapper.toDto(payCardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PayCardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PayCardDTO> findByCriteria(PayCardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PayCard> specification = createSpecification(criteria);
        return payCardRepository.findAll(specification, page)
            .map(payCardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PayCardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PayCard> specification = createSpecification(criteria);
        return payCardRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<PayCard> createSpecification(PayCardCriteria criteria) {
        Specification<PayCard> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PayCard_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), PayCard_.code));
            }
            if (criteria.getBranch() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBranch(), PayCard_.branch));
            }
            if (criteria.getAccountName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountName(), PayCard_.accountName));
            }
            if (criteria.getBankAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBankAccount(), PayCard_.bankAccount));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), PayCard_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), PayCard_.isHrVerify));
            }
            if (criteria.getDepositBankId() != null) {
                specification = specification.and(buildSpecification(criteria.getDepositBankId(),
                    root -> root.join(PayCard_.depositBank, JoinType.LEFT).get(EnumDepositBank_.id)));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(PayCard_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
