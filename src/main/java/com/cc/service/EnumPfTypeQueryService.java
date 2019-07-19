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

import com.cc.domain.EnumPfType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumPfTypeRepository;
import com.cc.service.dto.EnumPfTypeCriteria;
import com.cc.service.dto.EnumPfTypeDTO;
import com.cc.service.mapper.EnumPfTypeMapper;

/**
 * Service for executing complex queries for {@link EnumPfType} entities in the database.
 * The main input is a {@link EnumPfTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumPfTypeDTO} or a {@link Page} of {@link EnumPfTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumPfTypeQueryService extends QueryService<EnumPfType> {

    private final Logger log = LoggerFactory.getLogger(EnumPfTypeQueryService.class);

    private final EnumPfTypeRepository enumPfTypeRepository;

    private final EnumPfTypeMapper enumPfTypeMapper;

    public EnumPfTypeQueryService(EnumPfTypeRepository enumPfTypeRepository, EnumPfTypeMapper enumPfTypeMapper) {
        this.enumPfTypeRepository = enumPfTypeRepository;
        this.enumPfTypeMapper = enumPfTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumPfTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumPfTypeDTO> findByCriteria(EnumPfTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumPfType> specification = createSpecification(criteria);
        return enumPfTypeMapper.toDto(enumPfTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumPfTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumPfTypeDTO> findByCriteria(EnumPfTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumPfType> specification = createSpecification(criteria);
        return enumPfTypeRepository.findAll(specification, page)
            .map(enumPfTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumPfTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumPfType> specification = createSpecification(criteria);
        return enumPfTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumPfType> createSpecification(EnumPfTypeCriteria criteria) {
        Specification<EnumPfType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumPfType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumPfType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumPfType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumPfType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumPfType_.parent, JoinType.LEFT).get(EnumPfType_.id)));
            }
        }
        return specification;
    }
}
