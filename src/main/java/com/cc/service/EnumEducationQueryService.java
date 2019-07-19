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

import com.cc.domain.EnumEducation;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumEducationRepository;
import com.cc.service.dto.EnumEducationCriteria;
import com.cc.service.dto.EnumEducationDTO;
import com.cc.service.mapper.EnumEducationMapper;

/**
 * Service for executing complex queries for {@link EnumEducation} entities in the database.
 * The main input is a {@link EnumEducationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumEducationDTO} or a {@link Page} of {@link EnumEducationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumEducationQueryService extends QueryService<EnumEducation> {

    private final Logger log = LoggerFactory.getLogger(EnumEducationQueryService.class);

    private final EnumEducationRepository enumEducationRepository;

    private final EnumEducationMapper enumEducationMapper;

    public EnumEducationQueryService(EnumEducationRepository enumEducationRepository, EnumEducationMapper enumEducationMapper) {
        this.enumEducationRepository = enumEducationRepository;
        this.enumEducationMapper = enumEducationMapper;
    }

    /**
     * Return a {@link List} of {@link EnumEducationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumEducationDTO> findByCriteria(EnumEducationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumEducation> specification = createSpecification(criteria);
        return enumEducationMapper.toDto(enumEducationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumEducationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumEducationDTO> findByCriteria(EnumEducationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumEducation> specification = createSpecification(criteria);
        return enumEducationRepository.findAll(specification, page)
            .map(enumEducationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumEducationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumEducation> specification = createSpecification(criteria);
        return enumEducationRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumEducation> createSpecification(EnumEducationCriteria criteria) {
        Specification<EnumEducation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumEducation_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumEducation_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumEducation_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumEducation_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumEducation_.parent, JoinType.LEFT).get(EnumEducation_.id)));
            }
        }
        return specification;
    }
}
