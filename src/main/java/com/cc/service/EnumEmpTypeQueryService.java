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

import com.cc.domain.EnumEmpType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumEmpTypeRepository;
import com.cc.service.dto.EnumEmpTypeCriteria;
import com.cc.service.dto.EnumEmpTypeDTO;
import com.cc.service.mapper.EnumEmpTypeMapper;

/**
 * Service for executing complex queries for {@link EnumEmpType} entities in the database.
 * The main input is a {@link EnumEmpTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumEmpTypeDTO} or a {@link Page} of {@link EnumEmpTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumEmpTypeQueryService extends QueryService<EnumEmpType> {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTypeQueryService.class);

    private final EnumEmpTypeRepository enumEmpTypeRepository;

    private final EnumEmpTypeMapper enumEmpTypeMapper;

    public EnumEmpTypeQueryService(EnumEmpTypeRepository enumEmpTypeRepository, EnumEmpTypeMapper enumEmpTypeMapper) {
        this.enumEmpTypeRepository = enumEmpTypeRepository;
        this.enumEmpTypeMapper = enumEmpTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumEmpTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumEmpTypeDTO> findByCriteria(EnumEmpTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumEmpType> specification = createSpecification(criteria);
        return enumEmpTypeMapper.toDto(enumEmpTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumEmpTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumEmpTypeDTO> findByCriteria(EnumEmpTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumEmpType> specification = createSpecification(criteria);
        return enumEmpTypeRepository.findAll(specification, page)
            .map(enumEmpTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumEmpTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumEmpType> specification = createSpecification(criteria);
        return enumEmpTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumEmpType> createSpecification(EnumEmpTypeCriteria criteria) {
        Specification<EnumEmpType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumEmpType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumEmpType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumEmpType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumEmpType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumEmpType_.parent, JoinType.LEFT).get(EnumEmpType_.id)));
            }
        }
        return specification;
    }
}
