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

import com.cc.domain.EnumMaritalStatus;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumMaritalStatusRepository;
import com.cc.service.dto.EnumMaritalStatusCriteria;
import com.cc.service.dto.EnumMaritalStatusDTO;
import com.cc.service.mapper.EnumMaritalStatusMapper;

/**
 * Service for executing complex queries for {@link EnumMaritalStatus} entities in the database.
 * The main input is a {@link EnumMaritalStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumMaritalStatusDTO} or a {@link Page} of {@link EnumMaritalStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumMaritalStatusQueryService extends QueryService<EnumMaritalStatus> {

    private final Logger log = LoggerFactory.getLogger(EnumMaritalStatusQueryService.class);

    private final EnumMaritalStatusRepository enumMaritalStatusRepository;

    private final EnumMaritalStatusMapper enumMaritalStatusMapper;

    public EnumMaritalStatusQueryService(EnumMaritalStatusRepository enumMaritalStatusRepository, EnumMaritalStatusMapper enumMaritalStatusMapper) {
        this.enumMaritalStatusRepository = enumMaritalStatusRepository;
        this.enumMaritalStatusMapper = enumMaritalStatusMapper;
    }

    /**
     * Return a {@link List} of {@link EnumMaritalStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumMaritalStatusDTO> findByCriteria(EnumMaritalStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumMaritalStatus> specification = createSpecification(criteria);
        return enumMaritalStatusMapper.toDto(enumMaritalStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumMaritalStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumMaritalStatusDTO> findByCriteria(EnumMaritalStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumMaritalStatus> specification = createSpecification(criteria);
        return enumMaritalStatusRepository.findAll(specification, page)
            .map(enumMaritalStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumMaritalStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumMaritalStatus> specification = createSpecification(criteria);
        return enumMaritalStatusRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumMaritalStatus> createSpecification(EnumMaritalStatusCriteria criteria) {
        Specification<EnumMaritalStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumMaritalStatus_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumMaritalStatus_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumMaritalStatus_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumMaritalStatus_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumMaritalStatus_.parent, JoinType.LEFT).get(EnumMaritalStatus_.id)));
            }
        }
        return specification;
    }
}
