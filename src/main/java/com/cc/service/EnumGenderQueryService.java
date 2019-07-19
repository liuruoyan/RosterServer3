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

import com.cc.domain.EnumGender;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumGenderRepository;
import com.cc.service.dto.EnumGenderCriteria;
import com.cc.service.dto.EnumGenderDTO;
import com.cc.service.mapper.EnumGenderMapper;

/**
 * Service for executing complex queries for {@link EnumGender} entities in the database.
 * The main input is a {@link EnumGenderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumGenderDTO} or a {@link Page} of {@link EnumGenderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumGenderQueryService extends QueryService<EnumGender> {

    private final Logger log = LoggerFactory.getLogger(EnumGenderQueryService.class);

    private final EnumGenderRepository enumGenderRepository;

    private final EnumGenderMapper enumGenderMapper;

    public EnumGenderQueryService(EnumGenderRepository enumGenderRepository, EnumGenderMapper enumGenderMapper) {
        this.enumGenderRepository = enumGenderRepository;
        this.enumGenderMapper = enumGenderMapper;
    }

    /**
     * Return a {@link List} of {@link EnumGenderDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumGenderDTO> findByCriteria(EnumGenderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumGender> specification = createSpecification(criteria);
        return enumGenderMapper.toDto(enumGenderRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumGenderDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumGenderDTO> findByCriteria(EnumGenderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumGender> specification = createSpecification(criteria);
        return enumGenderRepository.findAll(specification, page)
            .map(enumGenderMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumGenderCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumGender> specification = createSpecification(criteria);
        return enumGenderRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumGender> createSpecification(EnumGenderCriteria criteria) {
        Specification<EnumGender> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumGender_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumGender_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumGender_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumGender_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumGender_.parent, JoinType.LEFT).get(EnumGender_.id)));
            }
        }
        return specification;
    }
}
