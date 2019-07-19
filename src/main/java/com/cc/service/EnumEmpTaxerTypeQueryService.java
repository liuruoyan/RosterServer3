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

import com.cc.domain.EnumEmpTaxerType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumEmpTaxerTypeRepository;
import com.cc.service.dto.EnumEmpTaxerTypeCriteria;
import com.cc.service.dto.EnumEmpTaxerTypeDTO;
import com.cc.service.mapper.EnumEmpTaxerTypeMapper;

/**
 * Service for executing complex queries for {@link EnumEmpTaxerType} entities in the database.
 * The main input is a {@link EnumEmpTaxerTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumEmpTaxerTypeDTO} or a {@link Page} of {@link EnumEmpTaxerTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumEmpTaxerTypeQueryService extends QueryService<EnumEmpTaxerType> {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTaxerTypeQueryService.class);

    private final EnumEmpTaxerTypeRepository enumEmpTaxerTypeRepository;

    private final EnumEmpTaxerTypeMapper enumEmpTaxerTypeMapper;

    public EnumEmpTaxerTypeQueryService(EnumEmpTaxerTypeRepository enumEmpTaxerTypeRepository, EnumEmpTaxerTypeMapper enumEmpTaxerTypeMapper) {
        this.enumEmpTaxerTypeRepository = enumEmpTaxerTypeRepository;
        this.enumEmpTaxerTypeMapper = enumEmpTaxerTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumEmpTaxerTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumEmpTaxerTypeDTO> findByCriteria(EnumEmpTaxerTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumEmpTaxerType> specification = createSpecification(criteria);
        return enumEmpTaxerTypeMapper.toDto(enumEmpTaxerTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumEmpTaxerTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumEmpTaxerTypeDTO> findByCriteria(EnumEmpTaxerTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumEmpTaxerType> specification = createSpecification(criteria);
        return enumEmpTaxerTypeRepository.findAll(specification, page)
            .map(enumEmpTaxerTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumEmpTaxerTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumEmpTaxerType> specification = createSpecification(criteria);
        return enumEmpTaxerTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumEmpTaxerType> createSpecification(EnumEmpTaxerTypeCriteria criteria) {
        Specification<EnumEmpTaxerType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumEmpTaxerType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumEmpTaxerType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumEmpTaxerType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumEmpTaxerType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumEmpTaxerType_.parent, JoinType.LEFT).get(EnumEmpTaxerType_.id)));
            }
        }
        return specification;
    }
}
