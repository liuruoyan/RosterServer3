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

import com.cc.domain.Dimission;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.DimissionRepository;
import com.cc.service.dto.DimissionCriteria;
import com.cc.service.dto.DimissionDTO;
import com.cc.service.mapper.DimissionMapper;

/**
 * Service for executing complex queries for {@link Dimission} entities in the database.
 * The main input is a {@link DimissionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DimissionDTO} or a {@link Page} of {@link DimissionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DimissionQueryService extends QueryService<Dimission> {

    private final Logger log = LoggerFactory.getLogger(DimissionQueryService.class);

    private final DimissionRepository dimissionRepository;

    private final DimissionMapper dimissionMapper;

    public DimissionQueryService(DimissionRepository dimissionRepository, DimissionMapper dimissionMapper) {
        this.dimissionRepository = dimissionRepository;
        this.dimissionMapper = dimissionMapper;
    }

    /**
     * Return a {@link List} of {@link DimissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DimissionDTO> findByCriteria(DimissionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Dimission> specification = createSpecification(criteria);
        return dimissionMapper.toDto(dimissionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DimissionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DimissionDTO> findByCriteria(DimissionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Dimission> specification = createSpecification(criteria);
        return dimissionRepository.findAll(specification, page)
            .map(dimissionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DimissionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Dimission> specification = createSpecification(criteria);
        return dimissionRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Dimission> createSpecification(DimissionCriteria criteria) {
        Specification<Dimission> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Dimission_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Dimission_.code));
            }
            if (criteria.getLastDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastDate(), Dimission_.lastDate));
            }
            if (criteria.getReason() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReason(), Dimission_.reason));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), Dimission_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), Dimission_.isHrVerify));
            }
            if (criteria.getDimissionTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getDimissionTypeId(),
                    root -> root.join(Dimission_.dimissionType, JoinType.LEFT).get(EnumDimissionType_.id)));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(Dimission_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
