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

import com.cc.domain.DirectSupervisor;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.DirectSupervisorRepository;
import com.cc.service.dto.DirectSupervisorCriteria;
import com.cc.service.dto.DirectSupervisorDTO;
import com.cc.service.mapper.DirectSupervisorMapper;

/**
 * Service for executing complex queries for {@link DirectSupervisor} entities in the database.
 * The main input is a {@link DirectSupervisorCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DirectSupervisorDTO} or a {@link Page} of {@link DirectSupervisorDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DirectSupervisorQueryService extends QueryService<DirectSupervisor> {

    private final Logger log = LoggerFactory.getLogger(DirectSupervisorQueryService.class);

    private final DirectSupervisorRepository directSupervisorRepository;

    private final DirectSupervisorMapper directSupervisorMapper;

    public DirectSupervisorQueryService(DirectSupervisorRepository directSupervisorRepository, DirectSupervisorMapper directSupervisorMapper) {
        this.directSupervisorRepository = directSupervisorRepository;
        this.directSupervisorMapper = directSupervisorMapper;
    }

    /**
     * Return a {@link List} of {@link DirectSupervisorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DirectSupervisorDTO> findByCriteria(DirectSupervisorCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DirectSupervisor> specification = createSpecification(criteria);
        return directSupervisorMapper.toDto(directSupervisorRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DirectSupervisorDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DirectSupervisorDTO> findByCriteria(DirectSupervisorCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DirectSupervisor> specification = createSpecification(criteria);
        return directSupervisorRepository.findAll(specification, page)
            .map(directSupervisorMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DirectSupervisorCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DirectSupervisor> specification = createSpecification(criteria);
        return directSupervisorRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<DirectSupervisor> createSpecification(DirectSupervisorCriteria criteria) {
        Specification<DirectSupervisor> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DirectSupervisor_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), DirectSupervisor_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DirectSupervisor_.name));
            }
            if (criteria.getPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhone(), DirectSupervisor_.phone));
            }
            if (criteria.getaSupName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getaSupName(), DirectSupervisor_.aSupName));
            }
            if (criteria.getaSupPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getaSupPhone(), DirectSupervisor_.aSupPhone));
            }
            if (criteria.getbSupName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getbSupName(), DirectSupervisor_.bSupName));
            }
            if (criteria.getbSupPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getbSupPhone(), DirectSupervisor_.bSupPhone));
            }
            if (criteria.getfSubName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getfSubName(), DirectSupervisor_.fSubName));
            }
            if (criteria.getfSubPhone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getfSubPhone(), DirectSupervisor_.fSubPhone));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), DirectSupervisor_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), DirectSupervisor_.isHrVerify));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(DirectSupervisor_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
