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

import com.cc.domain.Employee;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.EmployeeRepository;
import com.cc.service.dto.EmployeeCriteria;
import com.cc.service.dto.EmployeeDTO;
import com.cc.service.mapper.EmployeeMapper;

/**
 * Service for executing complex queries for {@link Employee} entities in the database.
 * The main input is a {@link EmployeeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmployeeDTO} or a {@link Page} of {@link EmployeeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmployeeQueryService extends QueryService<Employee> {

    private final Logger log = LoggerFactory.getLogger(EmployeeQueryService.class);

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeQueryService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    /**
     * Return a {@link List} of {@link EmployeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmployeeDTO> findByCriteria(EmployeeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Employee> specification = createSpecification(criteria);
        return employeeMapper.toDto(employeeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmployeeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> findByCriteria(EmployeeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Employee> specification = createSpecification(criteria);
        return employeeRepository.findAll(specification, page)
            .map(employeeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmployeeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Employee> specification = createSpecification(criteria);
        return employeeRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Employee> createSpecification(EmployeeCriteria criteria) {
        Specification<Employee> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Employee_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Employee_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Employee_.name));
            }
            if (criteria.getIdNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIdNumber(), Employee_.idNumber));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), Employee_.phone));
            }
            if (criteria.getHireDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getHireDate(), Employee_.hireDate));
            }
            if (criteria.getJobGrade() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobGrade(), Employee_.jobGrade));
            }
            if (criteria.getPosition() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPosition(), Employee_.position));
            }
            if (criteria.getJob() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJob(), Employee_.job));
            }
            if (criteria.getDeptName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDeptName(), Employee_.deptName));
            }
            if (criteria.getEmpNo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmpNo(), Employee_.empNo));
            }
            if (criteria.getSeniority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSeniority(), Employee_.seniority));
            }
            if (criteria.getContractor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContractor(), Employee_.contractor));
            }
            if (criteria.getBirthType() != null) {
                specification = specification.and(buildSpecification(criteria.getBirthType(), Employee_.birthType));
            }
            if (criteria.getBirthday() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthday(), Employee_.birthday));
            }
            if (criteria.getWorkLoc() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWorkLoc(), Employee_.workLoc));
            }
            if (criteria.getContactAddr() != null) {
                specification = specification.and(buildStringSpecification(criteria.getContactAddr(), Employee_.contactAddr));
            }
            if (criteria.getNationality() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationality(), Employee_.nationality));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstName(), Employee_.firstName));
            }
            if (criteria.getLastName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLastName(), Employee_.lastName));
            }
            if (criteria.getOthers() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOthers(), Employee_.others));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), Employee_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), Employee_.isHrVerify));
            }
            if (criteria.getContractsId() != null) {
                specification = specification.and(buildSpecification(criteria.getContractsId(),
                    root -> root.join(Employee_.contracts, JoinType.LEFT).get(Contract_.id)));
            }
            if (criteria.getPersonalsId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonalsId(),
                    root -> root.join(Employee_.personals, JoinType.LEFT).get(Personal_.id)));
            }
            if (criteria.getSocialSecurityBenefitsId() != null) {
                specification = specification.and(buildSpecification(criteria.getSocialSecurityBenefitsId(),
                    root -> root.join(Employee_.socialSecurityBenefits, JoinType.LEFT).get(SocialSecurityBenefits_.id)));
            }
            if (criteria.getPayCardsId() != null) {
                specification = specification.and(buildSpecification(criteria.getPayCardsId(),
                    root -> root.join(Employee_.payCards, JoinType.LEFT).get(PayCard_.id)));
            }
            if (criteria.getDimissionsId() != null) {
                specification = specification.and(buildSpecification(criteria.getDimissionsId(),
                    root -> root.join(Employee_.dimissions, JoinType.LEFT).get(Dimission_.id)));
            }
            if (criteria.getWorkExperiencesId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkExperiencesId(),
                    root -> root.join(Employee_.workExperiences, JoinType.LEFT).get(WorkExperience_.id)));
            }
            if (criteria.getEducationExperiencesId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationExperiencesId(),
                    root -> root.join(Employee_.educationExperiences, JoinType.LEFT).get(EducationExperience_.id)));
            }
            if (criteria.getDirectSupervisorsId() != null) {
                specification = specification.and(buildSpecification(criteria.getDirectSupervisorsId(),
                    root -> root.join(Employee_.directSupervisors, JoinType.LEFT).get(DirectSupervisor_.id)));
            }
            if (criteria.getAdditionalPostsId() != null) {
                specification = specification.and(buildSpecification(criteria.getAdditionalPostsId(),
                    root -> root.join(Employee_.additionalPosts, JoinType.LEFT).get(AdditionalPost_.id)));
            }
            if (criteria.getStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getStatusId(),
                    root -> root.join(Employee_.status, JoinType.LEFT).get(EnumEmpStatus_.id)));
            }
            if (criteria.getIdTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getIdTypeId(),
                    root -> root.join(Employee_.idType, JoinType.LEFT).get(EnumIdType_.id)));
            }
            if (criteria.getContractTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getContractTypeId(),
                    root -> root.join(Employee_.contractType, JoinType.LEFT).get(EnumContractType_.id)));
            }
            if (criteria.getEmpTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpTypeId(),
                    root -> root.join(Employee_.empType, JoinType.LEFT).get(EnumEmpType_.id)));
            }
            if (criteria.getGenderId() != null) {
                specification = specification.and(buildSpecification(criteria.getGenderId(),
                    root -> root.join(Employee_.gender, JoinType.LEFT).get(EnumGender_.id)));
            }
        }
        return specification;
    }
}
