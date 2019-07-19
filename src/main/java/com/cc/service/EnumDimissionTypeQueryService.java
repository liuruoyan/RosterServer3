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

import com.cc.domain.EnumDimissionType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumDimissionTypeRepository;
import com.cc.service.dto.EnumDimissionTypeCriteria;
import com.cc.service.dto.EnumDimissionTypeDTO;
import com.cc.service.mapper.EnumDimissionTypeMapper;

/**
 * Service for executing complex queries for {@link EnumDimissionType} entities in the database.
 * The main input is a {@link EnumDimissionTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumDimissionTypeDTO} or a {@link Page} of {@link EnumDimissionTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumDimissionTypeQueryService extends QueryService<EnumDimissionType> {

    private final Logger log = LoggerFactory.getLogger(EnumDimissionTypeQueryService.class);

    private final EnumDimissionTypeRepository enumDimissionTypeRepository;

    private final EnumDimissionTypeMapper enumDimissionTypeMapper;

    public EnumDimissionTypeQueryService(EnumDimissionTypeRepository enumDimissionTypeRepository, EnumDimissionTypeMapper enumDimissionTypeMapper) {
        this.enumDimissionTypeRepository = enumDimissionTypeRepository;
        this.enumDimissionTypeMapper = enumDimissionTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumDimissionTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumDimissionTypeDTO> findByCriteria(EnumDimissionTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumDimissionType> specification = createSpecification(criteria);
        return enumDimissionTypeMapper.toDto(enumDimissionTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumDimissionTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumDimissionTypeDTO> findByCriteria(EnumDimissionTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumDimissionType> specification = createSpecification(criteria);
        return enumDimissionTypeRepository.findAll(specification, page)
            .map(enumDimissionTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumDimissionTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumDimissionType> specification = createSpecification(criteria);
        return enumDimissionTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumDimissionType> createSpecification(EnumDimissionTypeCriteria criteria) {
        Specification<EnumDimissionType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumDimissionType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumDimissionType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumDimissionType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumDimissionType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumDimissionType_.parent, JoinType.LEFT).get(EnumDimissionType_.id)));
            }
        }
        return specification;
    }
}
