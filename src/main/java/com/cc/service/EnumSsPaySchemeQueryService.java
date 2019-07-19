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

import com.cc.domain.EnumSsPayScheme;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumSsPaySchemeRepository;
import com.cc.service.dto.EnumSsPaySchemeCriteria;
import com.cc.service.dto.EnumSsPaySchemeDTO;
import com.cc.service.mapper.EnumSsPaySchemeMapper;

/**
 * Service for executing complex queries for {@link EnumSsPayScheme} entities in the database.
 * The main input is a {@link EnumSsPaySchemeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumSsPaySchemeDTO} or a {@link Page} of {@link EnumSsPaySchemeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumSsPaySchemeQueryService extends QueryService<EnumSsPayScheme> {

    private final Logger log = LoggerFactory.getLogger(EnumSsPaySchemeQueryService.class);

    private final EnumSsPaySchemeRepository enumSsPaySchemeRepository;

    private final EnumSsPaySchemeMapper enumSsPaySchemeMapper;

    public EnumSsPaySchemeQueryService(EnumSsPaySchemeRepository enumSsPaySchemeRepository, EnumSsPaySchemeMapper enumSsPaySchemeMapper) {
        this.enumSsPaySchemeRepository = enumSsPaySchemeRepository;
        this.enumSsPaySchemeMapper = enumSsPaySchemeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumSsPaySchemeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumSsPaySchemeDTO> findByCriteria(EnumSsPaySchemeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumSsPayScheme> specification = createSpecification(criteria);
        return enumSsPaySchemeMapper.toDto(enumSsPaySchemeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumSsPaySchemeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumSsPaySchemeDTO> findByCriteria(EnumSsPaySchemeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumSsPayScheme> specification = createSpecification(criteria);
        return enumSsPaySchemeRepository.findAll(specification, page)
            .map(enumSsPaySchemeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumSsPaySchemeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumSsPayScheme> specification = createSpecification(criteria);
        return enumSsPaySchemeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumSsPayScheme> createSpecification(EnumSsPaySchemeCriteria criteria) {
        Specification<EnumSsPayScheme> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumSsPayScheme_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumSsPayScheme_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumSsPayScheme_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumSsPayScheme_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumSsPayScheme_.parent, JoinType.LEFT).get(EnumSsPayScheme_.id)));
            }
        }
        return specification;
    }
}
