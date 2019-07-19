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

import com.cc.domain.EnumEmpLaborType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumEmpLaborTypeRepository;
import com.cc.service.dto.EnumEmpLaborTypeCriteria;
import com.cc.service.dto.EnumEmpLaborTypeDTO;
import com.cc.service.mapper.EnumEmpLaborTypeMapper;

/**
 * Service for executing complex queries for {@link EnumEmpLaborType} entities in the database.
 * The main input is a {@link EnumEmpLaborTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumEmpLaborTypeDTO} or a {@link Page} of {@link EnumEmpLaborTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumEmpLaborTypeQueryService extends QueryService<EnumEmpLaborType> {

    private final Logger log = LoggerFactory.getLogger(EnumEmpLaborTypeQueryService.class);

    private final EnumEmpLaborTypeRepository enumEmpLaborTypeRepository;

    private final EnumEmpLaborTypeMapper enumEmpLaborTypeMapper;

    public EnumEmpLaborTypeQueryService(EnumEmpLaborTypeRepository enumEmpLaborTypeRepository, EnumEmpLaborTypeMapper enumEmpLaborTypeMapper) {
        this.enumEmpLaborTypeRepository = enumEmpLaborTypeRepository;
        this.enumEmpLaborTypeMapper = enumEmpLaborTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumEmpLaborTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumEmpLaborTypeDTO> findByCriteria(EnumEmpLaborTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumEmpLaborType> specification = createSpecification(criteria);
        return enumEmpLaborTypeMapper.toDto(enumEmpLaborTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumEmpLaborTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumEmpLaborTypeDTO> findByCriteria(EnumEmpLaborTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumEmpLaborType> specification = createSpecification(criteria);
        return enumEmpLaborTypeRepository.findAll(specification, page)
            .map(enumEmpLaborTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumEmpLaborTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumEmpLaborType> specification = createSpecification(criteria);
        return enumEmpLaborTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumEmpLaborType> createSpecification(EnumEmpLaborTypeCriteria criteria) {
        Specification<EnumEmpLaborType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumEmpLaborType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumEmpLaborType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumEmpLaborType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumEmpLaborType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumEmpLaborType_.parent, JoinType.LEFT).get(EnumEmpLaborType_.id)));
            }
        }
        return specification;
    }
}
