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

import com.cc.domain.EnumPfStatus;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumPfStatusRepository;
import com.cc.service.dto.EnumPfStatusCriteria;
import com.cc.service.dto.EnumPfStatusDTO;
import com.cc.service.mapper.EnumPfStatusMapper;

/**
 * Service for executing complex queries for {@link EnumPfStatus} entities in the database.
 * The main input is a {@link EnumPfStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumPfStatusDTO} or a {@link Page} of {@link EnumPfStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumPfStatusQueryService extends QueryService<EnumPfStatus> {

    private final Logger log = LoggerFactory.getLogger(EnumPfStatusQueryService.class);

    private final EnumPfStatusRepository enumPfStatusRepository;

    private final EnumPfStatusMapper enumPfStatusMapper;

    public EnumPfStatusQueryService(EnumPfStatusRepository enumPfStatusRepository, EnumPfStatusMapper enumPfStatusMapper) {
        this.enumPfStatusRepository = enumPfStatusRepository;
        this.enumPfStatusMapper = enumPfStatusMapper;
    }

    /**
     * Return a {@link List} of {@link EnumPfStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumPfStatusDTO> findByCriteria(EnumPfStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumPfStatus> specification = createSpecification(criteria);
        return enumPfStatusMapper.toDto(enumPfStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumPfStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumPfStatusDTO> findByCriteria(EnumPfStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumPfStatus> specification = createSpecification(criteria);
        return enumPfStatusRepository.findAll(specification, page)
            .map(enumPfStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumPfStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumPfStatus> specification = createSpecification(criteria);
        return enumPfStatusRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumPfStatus> createSpecification(EnumPfStatusCriteria criteria) {
        Specification<EnumPfStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumPfStatus_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumPfStatus_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumPfStatus_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumPfStatus_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumPfStatus_.parent, JoinType.LEFT).get(EnumPfStatus_.id)));
            }
        }
        return specification;
    }
}
