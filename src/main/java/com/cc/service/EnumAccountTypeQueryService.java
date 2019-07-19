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

import com.cc.domain.EnumAccountType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumAccountTypeRepository;
import com.cc.service.dto.EnumAccountTypeCriteria;
import com.cc.service.dto.EnumAccountTypeDTO;
import com.cc.service.mapper.EnumAccountTypeMapper;

/**
 * Service for executing complex queries for {@link EnumAccountType} entities in the database.
 * The main input is a {@link EnumAccountTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumAccountTypeDTO} or a {@link Page} of {@link EnumAccountTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumAccountTypeQueryService extends QueryService<EnumAccountType> {

    private final Logger log = LoggerFactory.getLogger(EnumAccountTypeQueryService.class);

    private final EnumAccountTypeRepository enumAccountTypeRepository;

    private final EnumAccountTypeMapper enumAccountTypeMapper;

    public EnumAccountTypeQueryService(EnumAccountTypeRepository enumAccountTypeRepository, EnumAccountTypeMapper enumAccountTypeMapper) {
        this.enumAccountTypeRepository = enumAccountTypeRepository;
        this.enumAccountTypeMapper = enumAccountTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumAccountTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumAccountTypeDTO> findByCriteria(EnumAccountTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumAccountType> specification = createSpecification(criteria);
        return enumAccountTypeMapper.toDto(enumAccountTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumAccountTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumAccountTypeDTO> findByCriteria(EnumAccountTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumAccountType> specification = createSpecification(criteria);
        return enumAccountTypeRepository.findAll(specification, page)
            .map(enumAccountTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumAccountTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumAccountType> specification = createSpecification(criteria);
        return enumAccountTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumAccountType> createSpecification(EnumAccountTypeCriteria criteria) {
        Specification<EnumAccountType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumAccountType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumAccountType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumAccountType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumAccountType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumAccountType_.parent, JoinType.LEFT).get(EnumAccountType_.id)));
            }
        }
        return specification;
    }
}
