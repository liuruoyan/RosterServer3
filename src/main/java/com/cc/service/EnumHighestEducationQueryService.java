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

import com.cc.domain.EnumHighestEducation;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumHighestEducationRepository;
import com.cc.service.dto.EnumHighestEducationCriteria;
import com.cc.service.dto.EnumHighestEducationDTO;
import com.cc.service.mapper.EnumHighestEducationMapper;

/**
 * Service for executing complex queries for {@link EnumHighestEducation} entities in the database.
 * The main input is a {@link EnumHighestEducationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumHighestEducationDTO} or a {@link Page} of {@link EnumHighestEducationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumHighestEducationQueryService extends QueryService<EnumHighestEducation> {

    private final Logger log = LoggerFactory.getLogger(EnumHighestEducationQueryService.class);

    private final EnumHighestEducationRepository enumHighestEducationRepository;

    private final EnumHighestEducationMapper enumHighestEducationMapper;

    public EnumHighestEducationQueryService(EnumHighestEducationRepository enumHighestEducationRepository, EnumHighestEducationMapper enumHighestEducationMapper) {
        this.enumHighestEducationRepository = enumHighestEducationRepository;
        this.enumHighestEducationMapper = enumHighestEducationMapper;
    }

    /**
     * Return a {@link List} of {@link EnumHighestEducationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumHighestEducationDTO> findByCriteria(EnumHighestEducationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumHighestEducation> specification = createSpecification(criteria);
        return enumHighestEducationMapper.toDto(enumHighestEducationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumHighestEducationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumHighestEducationDTO> findByCriteria(EnumHighestEducationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumHighestEducation> specification = createSpecification(criteria);
        return enumHighestEducationRepository.findAll(specification, page)
            .map(enumHighestEducationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumHighestEducationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumHighestEducation> specification = createSpecification(criteria);
        return enumHighestEducationRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumHighestEducation> createSpecification(EnumHighestEducationCriteria criteria) {
        Specification<EnumHighestEducation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumHighestEducation_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumHighestEducation_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumHighestEducation_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumHighestEducation_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumHighestEducation_.parent, JoinType.LEFT).get(EnumHighestEducation_.id)));
            }
        }
        return specification;
    }
}
