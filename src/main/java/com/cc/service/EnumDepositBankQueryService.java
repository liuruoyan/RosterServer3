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

import com.cc.domain.EnumDepositBank;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumDepositBankRepository;
import com.cc.service.dto.EnumDepositBankCriteria;
import com.cc.service.dto.EnumDepositBankDTO;
import com.cc.service.mapper.EnumDepositBankMapper;

/**
 * Service for executing complex queries for {@link EnumDepositBank} entities in the database.
 * The main input is a {@link EnumDepositBankCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumDepositBankDTO} or a {@link Page} of {@link EnumDepositBankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumDepositBankQueryService extends QueryService<EnumDepositBank> {

    private final Logger log = LoggerFactory.getLogger(EnumDepositBankQueryService.class);

    private final EnumDepositBankRepository enumDepositBankRepository;

    private final EnumDepositBankMapper enumDepositBankMapper;

    public EnumDepositBankQueryService(EnumDepositBankRepository enumDepositBankRepository, EnumDepositBankMapper enumDepositBankMapper) {
        this.enumDepositBankRepository = enumDepositBankRepository;
        this.enumDepositBankMapper = enumDepositBankMapper;
    }

    /**
     * Return a {@link List} of {@link EnumDepositBankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumDepositBankDTO> findByCriteria(EnumDepositBankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumDepositBank> specification = createSpecification(criteria);
        return enumDepositBankMapper.toDto(enumDepositBankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumDepositBankDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumDepositBankDTO> findByCriteria(EnumDepositBankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumDepositBank> specification = createSpecification(criteria);
        return enumDepositBankRepository.findAll(specification, page)
            .map(enumDepositBankMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumDepositBankCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumDepositBank> specification = createSpecification(criteria);
        return enumDepositBankRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumDepositBank> createSpecification(EnumDepositBankCriteria criteria) {
        Specification<EnumDepositBank> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumDepositBank_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumDepositBank_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumDepositBank_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumDepositBank_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumDepositBank_.parent, JoinType.LEFT).get(EnumDepositBank_.id)));
            }
        }
        return specification;
    }
}
