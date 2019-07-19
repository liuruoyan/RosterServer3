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

import com.cc.domain.AdditionalPost;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.AdditionalPostRepository;
import com.cc.service.dto.AdditionalPostCriteria;
import com.cc.service.dto.AdditionalPostDTO;
import com.cc.service.mapper.AdditionalPostMapper;

/**
 * Service for executing complex queries for {@link AdditionalPost} entities in the database.
 * The main input is a {@link AdditionalPostCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AdditionalPostDTO} or a {@link Page} of {@link AdditionalPostDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AdditionalPostQueryService extends QueryService<AdditionalPost> {

    private final Logger log = LoggerFactory.getLogger(AdditionalPostQueryService.class);

    private final AdditionalPostRepository additionalPostRepository;

    private final AdditionalPostMapper additionalPostMapper;

    public AdditionalPostQueryService(AdditionalPostRepository additionalPostRepository, AdditionalPostMapper additionalPostMapper) {
        this.additionalPostRepository = additionalPostRepository;
        this.additionalPostMapper = additionalPostMapper;
    }

    /**
     * Return a {@link List} of {@link AdditionalPostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AdditionalPostDTO> findByCriteria(AdditionalPostCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AdditionalPost> specification = createSpecification(criteria);
        return additionalPostMapper.toDto(additionalPostRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AdditionalPostDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AdditionalPostDTO> findByCriteria(AdditionalPostCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AdditionalPost> specification = createSpecification(criteria);
        return additionalPostRepository.findAll(specification, page)
            .map(additionalPostMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AdditionalPostCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AdditionalPost> specification = createSpecification(criteria);
        return additionalPostRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<AdditionalPost> createSpecification(AdditionalPostCriteria criteria) {
        Specification<AdditionalPost> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AdditionalPost_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), AdditionalPost_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), AdditionalPost_.name));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), AdditionalPost_.phone));
            }
            if (criteria.getDept() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDept(), AdditionalPost_.dept));
            }
            if (criteria.getJob() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJob(), AdditionalPost_.job));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), AdditionalPost_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), AdditionalPost_.endDate));
            }
            if (criteria.getRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemark(), AdditionalPost_.remark));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), AdditionalPost_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), AdditionalPost_.isHrVerify));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(AdditionalPost_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
