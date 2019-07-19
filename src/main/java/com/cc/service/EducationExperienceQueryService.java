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

import com.cc.domain.EducationExperience;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EducationExperienceRepository;
import com.cc.service.dto.EducationExperienceCriteria;
import com.cc.service.dto.EducationExperienceDTO;
import com.cc.service.mapper.EducationExperienceMapper;

/**
 * Service for executing complex queries for {@link EducationExperience} entities in the database.
 * The main input is a {@link EducationExperienceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationExperienceDTO} or a {@link Page} of {@link EducationExperienceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationExperienceQueryService extends QueryService<EducationExperience> {

    private final Logger log = LoggerFactory.getLogger(EducationExperienceQueryService.class);

    private final EducationExperienceRepository educationExperienceRepository;

    private final EducationExperienceMapper educationExperienceMapper;

    public EducationExperienceQueryService(EducationExperienceRepository educationExperienceRepository, EducationExperienceMapper educationExperienceMapper) {
        this.educationExperienceRepository = educationExperienceRepository;
        this.educationExperienceMapper = educationExperienceMapper;
    }

    /**
     * Return a {@link List} of {@link EducationExperienceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationExperienceDTO> findByCriteria(EducationExperienceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationExperience> specification = createSpecification(criteria);
        return educationExperienceMapper.toDto(educationExperienceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationExperienceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationExperienceDTO> findByCriteria(EducationExperienceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationExperience> specification = createSpecification(criteria);
        return educationExperienceRepository.findAll(specification, page)
            .map(educationExperienceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationExperienceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationExperience> specification = createSpecification(criteria);
        return educationExperienceRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<EducationExperience> createSpecification(EducationExperienceCriteria criteria) {
        Specification<EducationExperience> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationExperience_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EducationExperience_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), EducationExperience_.name));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), EducationExperience_.phone));
            }
            if (criteria.getSchool() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSchool(), EducationExperience_.school));
            }
            if (criteria.getMajor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMajor(), EducationExperience_.major));
            }
            if (criteria.getInDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInDate(), EducationExperience_.inDate));
            }
            if (criteria.getGraduationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGraduationDate(), EducationExperience_.graduationDate));
            }
            if (criteria.getInception() != null) {
                specification = specification.and(buildSpecification(criteria.getInception(), EducationExperience_.inception));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), EducationExperience_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), EducationExperience_.isHrVerify));
            }
            if (criteria.getEducationId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationId(),
                    root -> root.join(EducationExperience_.education, JoinType.LEFT).get(EnumEducation_.id)));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(EducationExperience_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
