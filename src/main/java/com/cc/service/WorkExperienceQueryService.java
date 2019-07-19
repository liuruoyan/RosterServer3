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

import com.cc.domain.WorkExperience;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.WorkExperienceRepository;
import com.cc.service.dto.WorkExperienceCriteria;
import com.cc.service.dto.WorkExperienceDTO;
import com.cc.service.mapper.WorkExperienceMapper;

/**
 * Service for executing complex queries for {@link WorkExperience} entities in the database.
 * The main input is a {@link WorkExperienceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkExperienceDTO} or a {@link Page} of {@link WorkExperienceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkExperienceQueryService extends QueryService<WorkExperience> {

    private final Logger log = LoggerFactory.getLogger(WorkExperienceQueryService.class);

    private final WorkExperienceRepository workExperienceRepository;

    private final WorkExperienceMapper workExperienceMapper;

    public WorkExperienceQueryService(WorkExperienceRepository workExperienceRepository, WorkExperienceMapper workExperienceMapper) {
        this.workExperienceRepository = workExperienceRepository;
        this.workExperienceMapper = workExperienceMapper;
    }

    /**
     * Return a {@link List} of {@link WorkExperienceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkExperienceDTO> findByCriteria(WorkExperienceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkExperience> specification = createSpecification(criteria);
        return workExperienceMapper.toDto(workExperienceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkExperienceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkExperienceDTO> findByCriteria(WorkExperienceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkExperience> specification = createSpecification(criteria);
        return workExperienceRepository.findAll(specification, page)
            .map(workExperienceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkExperienceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkExperience> specification = createSpecification(criteria);
        return workExperienceRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<WorkExperience> createSpecification(WorkExperienceCriteria criteria) {
        Specification<WorkExperience> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), WorkExperience_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), WorkExperience_.code));
            }
            if (criteria.geteName() != null) {
                specification = specification.and(buildStringSpecification(criteria.geteName(), WorkExperience_.eName));
            }
            if (criteria.getPhoneNum() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNum(), WorkExperience_.phoneNum));
            }
            if (criteria.getCompany() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCompany(), WorkExperience_.company));
            }
            if (criteria.getJob() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJob(), WorkExperience_.job));
            }
            if (criteria.getJobDesc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobDesc(), WorkExperience_.jobDesc));
            }
            if (criteria.getHireDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHireDate(), WorkExperience_.hireDate));
            }
            if (criteria.getLeaveDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLeaveDate(), WorkExperience_.leaveDate));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), WorkExperience_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), WorkExperience_.isHrVerify));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(WorkExperience_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
