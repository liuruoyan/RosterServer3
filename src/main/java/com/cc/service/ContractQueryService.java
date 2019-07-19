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

import com.cc.domain.Contract;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.ContractRepository;
import com.cc.service.dto.ContractCriteria;
import com.cc.service.dto.ContractDTO;
import com.cc.service.mapper.ContractMapper;

/**
 * Service for executing complex queries for {@link Contract} entities in the database.
 * The main input is a {@link ContractCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ContractDTO} or a {@link Page} of {@link ContractDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ContractQueryService extends QueryService<Contract> {

    private final Logger log = LoggerFactory.getLogger(ContractQueryService.class);

    private final ContractRepository contractRepository;

    private final ContractMapper contractMapper;

    public ContractQueryService(ContractRepository contractRepository, ContractMapper contractMapper) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
    }

    /**
     * Return a {@link List} of {@link ContractDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ContractDTO> findByCriteria(ContractCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Contract> specification = createSpecification(criteria);
        return contractMapper.toDto(contractRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ContractDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ContractDTO> findByCriteria(ContractCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Contract> specification = createSpecification(criteria);
        return contractRepository.findAll(specification, page)
            .map(contractMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ContractCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Contract> specification = createSpecification(criteria);
        return contractRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Contract> createSpecification(ContractCriteria criteria) {
        Specification<Contract> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Contract_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Contract_.code));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), Contract_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), Contract_.endDate));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Contract_.email));
            }
            if (criteria.getWorkTel() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWorkTel(), Contract_.workTel));
            }
            if (criteria.getProbationEndDay() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProbationEndDay(), Contract_.probationEndDay));
            }
            if (criteria.getProbationLength() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProbationLength(), Contract_.probationLength));
            }
            if (criteria.getOther() != null) {
                specification = specification.and(buildStringSpecification(criteria.getOther(), Contract_.other));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), Contract_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), Contract_.isHrVerify));
            }
            if (criteria.getContractTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getContractTypeId(),
                    root -> root.join(Contract_.contractType, JoinType.LEFT).get(EnumContractType_.id)));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(Contract_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
