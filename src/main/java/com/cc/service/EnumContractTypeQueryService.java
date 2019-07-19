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

import com.cc.domain.EnumContractType;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EnumContractTypeRepository;
import com.cc.service.dto.EnumContractTypeCriteria;
import com.cc.service.dto.EnumContractTypeDTO;
import com.cc.service.mapper.EnumContractTypeMapper;

/**
 * Service for executing complex queries for {@link EnumContractType} entities in the database.
 * The main input is a {@link EnumContractTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EnumContractTypeDTO} or a {@link Page} of {@link EnumContractTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EnumContractTypeQueryService extends QueryService<EnumContractType> {

    private final Logger log = LoggerFactory.getLogger(EnumContractTypeQueryService.class);

    private final EnumContractTypeRepository enumContractTypeRepository;

    private final EnumContractTypeMapper enumContractTypeMapper;

    public EnumContractTypeQueryService(EnumContractTypeRepository enumContractTypeRepository, EnumContractTypeMapper enumContractTypeMapper) {
        this.enumContractTypeRepository = enumContractTypeRepository;
        this.enumContractTypeMapper = enumContractTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EnumContractTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EnumContractTypeDTO> findByCriteria(EnumContractTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EnumContractType> specification = createSpecification(criteria);
        return enumContractTypeMapper.toDto(enumContractTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EnumContractTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EnumContractTypeDTO> findByCriteria(EnumContractTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EnumContractType> specification = createSpecification(criteria);
        return enumContractTypeRepository.findAll(specification, page)
            .map(enumContractTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EnumContractTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EnumContractType> specification = createSpecification(criteria);
        return enumContractTypeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EnumContractType> createSpecification(EnumContractTypeCriteria criteria) {
        Specification<EnumContractType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EnumContractType_.id));
            }
            if (criteria.getValuez() != null) {
                specification = specification.and(buildStringSpecification(criteria.getValuez(), EnumContractType_.valuez));
            }
            if (criteria.getOrderz() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getOrderz(), EnumContractType_.orderz));
            }
            if (criteria.getTenentCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTenentCode(), EnumContractType_.tenentCode));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(EnumContractType_.parent, JoinType.LEFT).get(EnumContractType_.id)));
            }
        }
        return specification;
    }
}
