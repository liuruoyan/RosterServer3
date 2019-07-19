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

import com.cc.domain.EnumIdType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumIdTypeRepository;
import com.cc.service.dto.EnumIdTypeCriteria;
import com.cc.service.dto.EnumIdTypeDTO;
import com.cc.service.mapper.EnumIdTypeMapper;

/**
 * Service for executing complex queries for {@link EnumIdType} entities in the database.
 * The main input is a {@link EnumIdTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumIdTypeDTO} or a {@link Page} of {@link EnumIdTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumIdTypeQueryService extends QueryService<EnumIdType> {

    private final Logger log = LoggerFactory.getLogger(EnumIdTypeQueryService.class);

    private final EnumIdTypeRepository enumIdTypeRepository;

    private final EnumIdTypeMapper enumIdTypeMapper;

    public EnumIdTypeQueryService(EnumIdTypeRepository enumIdTypeRepository, EnumIdTypeMapper enumIdTypeMapper) {
        this.enumIdTypeRepository = enumIdTypeRepository;
        this.enumIdTypeMapper = enumIdTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumIdTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumIdTypeDTO> findByCriteria(EnumIdTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumIdType> specification = createSpecification(criteria);
        return enumIdTypeMapper.toDto(enumIdTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumIdTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumIdTypeDTO> findByCriteria(EnumIdTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumIdType> specification = createSpecification(criteria);
        return enumIdTypeRepository.findAll(specification, page)
            .map(enumIdTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumIdTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumIdType> specification = createSpecification(criteria);
        return enumIdTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumIdType> createSpecification(EnumIdTypeCriteria criteria) {
        Specification<EnumIdType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumIdType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumIdType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumIdType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumIdType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumIdType_.parent, JoinType.LEFT).get(EnumIdType_.id)));
            }
        }
        return specification;
    }
}
