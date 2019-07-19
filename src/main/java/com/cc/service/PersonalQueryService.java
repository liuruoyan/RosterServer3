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

import com.cc.domain.Personal;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.PersonalRepository;
import com.cc.service.dto.PersonalCriteria;
import com.cc.service.dto.PersonalDTO;
import com.cc.service.mapper.PersonalMapper;

/**
 * Service for executing complex queries for {@link Personal} entities in the database.
 * The main input is a {@link PersonalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PersonalDTO} or a {@link Page} of {@link PersonalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PersonalQueryService extends QueryService<Personal> {

    private final Logger log = LoggerFactory.getLogger(PersonalQueryService.class);

    private final PersonalRepository personalRepository;

    private final PersonalMapper personalMapper;

    public PersonalQueryService(PersonalRepository personalRepository, PersonalMapper personalMapper) {
        this.personalRepository = personalRepository;
        this.personalMapper = personalMapper;
    }

    /**
     * Return a {@link List} of {@link PersonalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PersonalDTO> findByCriteria(PersonalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Personal> specification = createSpecification(criteria);
        return personalMapper.toDto(personalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PersonalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonalDTO> findByCriteria(PersonalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Personal> specification = createSpecification(criteria);
        return personalRepository.findAll(specification, page)
            .map(personalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PersonalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Personal> specification = createSpecification(criteria);
        return personalRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Personal> createSpecification(PersonalCriteria criteria) {
        Specification<Personal> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Personal_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Personal_.code));
            }
            if (criteria.getStageName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStageName(), Personal_.stageName));
            }
            if (criteria.getIdName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdName(), Personal_.idName));
            }
            if (criteria.getNation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNation(), Personal_.nation));
            }
            if (criteria.getAccountLoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAccountLoc(), Personal_.accountLoc));
            }
            if (criteria.getNativePlace() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNativePlace(), Personal_.nativePlace));
            }
            if (criteria.getCurrentAddr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCurrentAddr(), Personal_.currentAddr));
            }
            if (criteria.getSpouseName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSpouseName(), Personal_.spouseName));
            }
            if (criteria.getChildName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChildName(), Personal_.childName));
            }
            if (criteria.getBloodType() != null) {
                specification = specification.and(buildSpecification(criteria.getBloodType(), Personal_.bloodType));
            }
            if (criteria.getEmergencyContactName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmergencyContactName(), Personal_.emergencyContactName));
            }
            if (criteria.getEmergencyContactPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmergencyContactPhone(), Personal_.emergencyContactPhone));
            }
            if (criteria.getQq() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQq(), Personal_.qq));
            }
            if (criteria.getWechat() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWechat(), Personal_.wechat));
            }
            if (criteria.getPersonalEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonalEmail(), Personal_.personalEmail));
            }
            if (criteria.getRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemark(), Personal_.remark));
            }
            if (criteria.getOthers() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOthers(), Personal_.others));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), Personal_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), Personal_.isHrVerify));
            }
            if (criteria.getAccountTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getAccountTypeId(),
                    root -> root.join(Personal_.accountType, JoinType.LEFT).get(EnumAccountType_.id)));
            }
            if (criteria.getHighestEducationId() != null) {
                specification = specification.and(buildSpecification(criteria.getHighestEducationId(),
                    root -> root.join(Personal_.highestEducation, JoinType.LEFT).get(EnumHighestEducation_.id)));
            }
            if (criteria.getPoliticsStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getPoliticsStatusId(),
                    root -> root.join(Personal_.politicsStatus, JoinType.LEFT).get(EnumPoliticsStatus_.id)));
            }
            if (criteria.getMaritalStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getMaritalStatusId(),
                    root -> root.join(Personal_.maritalStatus, JoinType.LEFT).get(EnumMaritalStatus_.id)));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(Personal_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
