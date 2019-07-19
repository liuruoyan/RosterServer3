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

import com.cc.domain.EnumSsStatus;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumSsStatusRepository;
import com.cc.service.dto.EnumSsStatusCriteria;
import com.cc.service.dto.EnumSsStatusDTO;
import com.cc.service.mapper.EnumSsStatusMapper;

/**
 * Service for executing complex queries for {@link EnumSsStatus} entities in the database.
 * The main input is a {@link EnumSsStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumSsStatusDTO} or a {@link Page} of {@link EnumSsStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumSsStatusQueryService extends QueryService<EnumSsStatus> {

    private final Logger log = LoggerFactory.getLogger(EnumSsStatusQueryService.class);

    private final EnumSsStatusRepository enumSsStatusRepository;

    private final EnumSsStatusMapper enumSsStatusMapper;

    public EnumSsStatusQueryService(EnumSsStatusRepository enumSsStatusRepository, EnumSsStatusMapper enumSsStatusMapper) {
        this.enumSsStatusRepository = enumSsStatusRepository;
        this.enumSsStatusMapper = enumSsStatusMapper;
    }

    /**
     * Return a {@link List} of {@link EnumSsStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumSsStatusDTO> findByCriteria(EnumSsStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumSsStatus> specification = createSpecification(criteria);
        return enumSsStatusMapper.toDto(enumSsStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumSsStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumSsStatusDTO> findByCriteria(EnumSsStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumSsStatus> specification = createSpecification(criteria);
        return enumSsStatusRepository.findAll(specification, page)
            .map(enumSsStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumSsStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumSsStatus> specification = createSpecification(criteria);
        return enumSsStatusRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumSsStatus> createSpecification(EnumSsStatusCriteria criteria) {
        Specification<EnumSsStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumSsStatus_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumSsStatus_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumSsStatus_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumSsStatus_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumSsStatus_.parent, JoinType.LEFT).get(EnumSsStatus_.id)));
            }
        }
        return specification;
    }
}
