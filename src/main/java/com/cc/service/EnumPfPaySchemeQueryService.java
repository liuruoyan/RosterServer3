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

import com.cc.domain.EnumPfPayScheme;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumPfPaySchemeRepository;
import com.cc.service.dto.EnumPfPaySchemeCriteria;
import com.cc.service.dto.EnumPfPaySchemeDTO;
import com.cc.service.mapper.EnumPfPaySchemeMapper;

/**
 * Service for executing complex queries for {@link EnumPfPayScheme} entities in the database.
 * The main input is a {@link EnumPfPaySchemeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumPfPaySchemeDTO} or a {@link Page} of {@link EnumPfPaySchemeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumPfPaySchemeQueryService extends QueryService<EnumPfPayScheme> {

    private final Logger log = LoggerFactory.getLogger(EnumPfPaySchemeQueryService.class);

    private final EnumPfPaySchemeRepository enumPfPaySchemeRepository;

    private final EnumPfPaySchemeMapper enumPfPaySchemeMapper;

    public EnumPfPaySchemeQueryService(EnumPfPaySchemeRepository enumPfPaySchemeRepository, EnumPfPaySchemeMapper enumPfPaySchemeMapper) {
        this.enumPfPaySchemeRepository = enumPfPaySchemeRepository;
        this.enumPfPaySchemeMapper = enumPfPaySchemeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumPfPaySchemeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumPfPaySchemeDTO> findByCriteria(EnumPfPaySchemeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumPfPayScheme> specification = createSpecification(criteria);
        return enumPfPaySchemeMapper.toDto(enumPfPaySchemeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumPfPaySchemeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumPfPaySchemeDTO> findByCriteria(EnumPfPaySchemeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumPfPayScheme> specification = createSpecification(criteria);
        return enumPfPaySchemeRepository.findAll(specification, page)
            .map(enumPfPaySchemeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumPfPaySchemeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumPfPayScheme> specification = createSpecification(criteria);
        return enumPfPaySchemeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumPfPayScheme> createSpecification(EnumPfPaySchemeCriteria criteria) {
        Specification<EnumPfPayScheme> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumPfPayScheme_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumPfPayScheme_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumPfPayScheme_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumPfPayScheme_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumPfPayScheme_.parent, JoinType.LEFT).get(EnumPfPayScheme_.id)));
            }
        }
        return specification;
    }
}
