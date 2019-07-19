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

import com.cc.domain.EnumPoliticsStatus;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumPoliticsStatusRepository;
import com.cc.service.dto.EnumPoliticsStatusCriteria;
import com.cc.service.dto.EnumPoliticsStatusDTO;
import com.cc.service.mapper.EnumPoliticsStatusMapper;

/**
 * Service for executing complex queries for {@link EnumPoliticsStatus} entities in the database.
 * The main input is a {@link EnumPoliticsStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumPoliticsStatusDTO} or a {@link Page} of {@link EnumPoliticsStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumPoliticsStatusQueryService extends QueryService<EnumPoliticsStatus> {

    private final Logger log = LoggerFactory.getLogger(EnumPoliticsStatusQueryService.class);

    private final EnumPoliticsStatusRepository enumPoliticsStatusRepository;

    private final EnumPoliticsStatusMapper enumPoliticsStatusMapper;

    public EnumPoliticsStatusQueryService(EnumPoliticsStatusRepository enumPoliticsStatusRepository, EnumPoliticsStatusMapper enumPoliticsStatusMapper) {
        this.enumPoliticsStatusRepository = enumPoliticsStatusRepository;
        this.enumPoliticsStatusMapper = enumPoliticsStatusMapper;
    }

    /**
     * Return a {@link List} of {@link EnumPoliticsStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumPoliticsStatusDTO> findByCriteria(EnumPoliticsStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumPoliticsStatus> specification = createSpecification(criteria);
        return enumPoliticsStatusMapper.toDto(enumPoliticsStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumPoliticsStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumPoliticsStatusDTO> findByCriteria(EnumPoliticsStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumPoliticsStatus> specification = createSpecification(criteria);
        return enumPoliticsStatusRepository.findAll(specification, page)
            .map(enumPoliticsStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumPoliticsStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumPoliticsStatus> specification = createSpecification(criteria);
        return enumPoliticsStatusRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumPoliticsStatus> createSpecification(EnumPoliticsStatusCriteria criteria) {
        Specification<EnumPoliticsStatus> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumPoliticsStatus_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumPoliticsStatus_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumPoliticsStatus_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumPoliticsStatus_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumPoliticsStatus_.parent, JoinType.LEFT).get(EnumPoliticsStatus_.id)));
            }
        }
        return specification;
    }
}
