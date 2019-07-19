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

import com.cc.domain.EnumEmpStatus;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumEmpStatusRepository;
import com.cc.service.dto.EnumEmpStatusCriteria;
import com.cc.service.dto.EnumEmpStatusDTO;
import com.cc.service.mapper.EnumEmpStatusMapper;

/**
 * Service for executing complex queries for {@link EnumEmpStatus} entities in the database.
 * The main input is a {@link EnumEmpStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumEmpStatusDTO} or a {@link Page} of {@link EnumEmpStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumEmpStatusQueryService extends QueryService<EnumEmpStatus> {

    private final Logger log = LoggerFactory.getLogger(EnumEmpStatusQueryService.class);

    private final EnumEmpStatusRepository enumEmpStatusRepository;

    private final EnumEmpStatusMapper enumEmpStatusMapper;

    public EnumEmpStatusQueryService(EnumEmpStatusRepository enumEmpStatusRepository, EnumEmpStatusMapper enumEmpStatusMapper) {
        this.enumEmpStatusRepository = enumEmpStatusRepository;
        this.enumEmpStatusMapper = enumEmpStatusMapper;
    }

    /**
     * Return a {@link List} of {@link EnumEmpStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumEmpStatusDTO> findByCriteria(EnumEmpStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumEmpStatus> specification = createSpecification(criteria);
        return enumEmpStatusMapper.toDto(enumEmpStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumEmpStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumEmpStatusDTO> findByCriteria(EnumEmpStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumEmpStatus> specification = createSpecification(criteria);
        return enumEmpStatusRepository.findAll(specification, page)
            .map(enumEmpStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumEmpStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumEmpStatus> specification = createSpecification(criteria);
        return enumEmpStatusRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumEmpStatus> createSpecification(EnumEmpStatusCriteria criteria) {
        Specification<EnumEmpStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumEmpStatus_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumEmpStatus_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumEmpStatus_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumEmpStatus_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumEmpStatus_.parent, JoinType.LEFT).get(EnumEmpStatus_.id)));
            }
        }
        return specification;
    }
}
