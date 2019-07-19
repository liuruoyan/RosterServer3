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

import com.cc.domain.EnumEmpTaxArea;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumEmpTaxAreaRepository;
import com.cc.service.dto.EnumEmpTaxAreaCriteria;
import com.cc.service.dto.EnumEmpTaxAreaDTO;
import com.cc.service.mapper.EnumEmpTaxAreaMapper;

/**
 * Service for executing complex queries for {@link EnumEmpTaxArea} entities in the database.
 * The main input is a {@link EnumEmpTaxAreaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumEmpTaxAreaDTO} or a {@link Page} of {@link EnumEmpTaxAreaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumEmpTaxAreaQueryService extends QueryService<EnumEmpTaxArea> {

    private final Logger log = LoggerFactory.getLogger(EnumEmpTaxAreaQueryService.class);

    private final EnumEmpTaxAreaRepository enumEmpTaxAreaRepository;

    private final EnumEmpTaxAreaMapper enumEmpTaxAreaMapper;

    public EnumEmpTaxAreaQueryService(EnumEmpTaxAreaRepository enumEmpTaxAreaRepository, EnumEmpTaxAreaMapper enumEmpTaxAreaMapper) {
        this.enumEmpTaxAreaRepository = enumEmpTaxAreaRepository;
        this.enumEmpTaxAreaMapper = enumEmpTaxAreaMapper;
    }

    /**
     * Return a {@link List} of {@link EnumEmpTaxAreaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumEmpTaxAreaDTO> findByCriteria(EnumEmpTaxAreaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumEmpTaxArea> specification = createSpecification(criteria);
        return enumEmpTaxAreaMapper.toDto(enumEmpTaxAreaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumEmpTaxAreaDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumEmpTaxAreaDTO> findByCriteria(EnumEmpTaxAreaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumEmpTaxArea> specification = createSpecification(criteria);
        return enumEmpTaxAreaRepository.findAll(specification, page)
            .map(enumEmpTaxAreaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumEmpTaxAreaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumEmpTaxArea> specification = createSpecification(criteria);
        return enumEmpTaxAreaRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumEmpTaxArea> createSpecification(EnumEmpTaxAreaCriteria criteria) {
        Specification<EnumEmpTaxArea> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumEmpTaxArea_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumEmpTaxArea_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumEmpTaxArea_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumEmpTaxArea_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumEmpTaxArea_.parent, JoinType.LEFT).get(EnumEmpTaxArea_.id)));
            }
        }
        return specification;
    }
}
