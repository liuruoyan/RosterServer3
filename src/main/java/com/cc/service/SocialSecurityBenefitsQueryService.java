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

import com.cc.domain.SocialSecurityBenefits;
import com.cc.domain.*; // for static metamodels
import com.cc.repository.SocialSecurityBenefitsRepository;
import com.cc.service.dto.SocialSecurityBenefitsCriteria;
import com.cc.service.dto.SocialSecurityBenefitsDTO;
import com.cc.service.mapper.SocialSecurityBenefitsMapper;

/**
 * Service for executing complex queries for {@link SocialSecurityBenefits} entities in the database.
 * The main input is a {@link SocialSecurityBenefitsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SocialSecurityBenefitsDTO} or a {@link Page} of {@link SocialSecurityBenefitsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SocialSecurityBenefitsQueryService extends QueryService<SocialSecurityBenefits> {

    private final Logger log = LoggerFactory.getLogger(SocialSecurityBenefitsQueryService.class);

    private final SocialSecurityBenefitsRepository socialSecurityBenefitsRepository;

    private final SocialSecurityBenefitsMapper socialSecurityBenefitsMapper;

    public SocialSecurityBenefitsQueryService(SocialSecurityBenefitsRepository socialSecurityBenefitsRepository, SocialSecurityBenefitsMapper socialSecurityBenefitsMapper) {
        this.socialSecurityBenefitsRepository = socialSecurityBenefitsRepository;
        this.socialSecurityBenefitsMapper = socialSecurityBenefitsMapper;
    }

    /**
     * Return a {@link List} of {@link SocialSecurityBenefitsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SocialSecurityBenefitsDTO> findByCriteria(SocialSecurityBenefitsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SocialSecurityBenefits> specification = createSpecification(criteria);
        return socialSecurityBenefitsMapper.toDto(socialSecurityBenefitsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SocialSecurityBenefitsDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SocialSecurityBenefitsDTO> findByCriteria(SocialSecurityBenefitsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SocialSecurityBenefits> specification = createSpecification(criteria);
        return socialSecurityBenefitsRepository.findAll(specification, page)
            .map(socialSecurityBenefitsMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SocialSecurityBenefitsCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SocialSecurityBenefits> specification = createSpecification(criteria);
        return socialSecurityBenefitsRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<SocialSecurityBenefits> createSpecification(SocialSecurityBenefitsCriteria criteria) {
        Specification<SocialSecurityBenefits> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SocialSecurityBenefits_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), SocialSecurityBenefits_.code));
            }
            if (criteria.getPfAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPfAccount(), SocialSecurityBenefits_.pfAccount));
            }
            if (criteria.getSpfAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSpfAccount(), SocialSecurityBenefits_.spfAccount));
            }
            if (criteria.getPfStartMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPfStartMonth(), SocialSecurityBenefits_.pfStartMonth));
            }
            if (criteria.getPfBase() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPfBase(), SocialSecurityBenefits_.pfBase));
            }
            if (criteria.getPfStopMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPfStopMonth(), SocialSecurityBenefits_.pfStopMonth));
            }
            if (criteria.getPfRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPfRemark(), SocialSecurityBenefits_.pfRemark));
            }
            if (criteria.getSsAccount() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSsAccount(), SocialSecurityBenefits_.ssAccount));
            }
            if (criteria.getSsCity() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSsCity(), SocialSecurityBenefits_.ssCity));
            }
            if (criteria.getSsStartMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSsStartMonth(), SocialSecurityBenefits_.ssStartMonth));
            }
            if (criteria.getSsBase() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSsBase(), SocialSecurityBenefits_.ssBase));
            }
            if (criteria.getSsStopMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSsStopMonth(), SocialSecurityBenefits_.ssStopMonth));
            }
            if (criteria.getSsRemark() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSsRemark(), SocialSecurityBenefits_.ssRemark));
            }
            if (criteria.getAllowance() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAllowance(), SocialSecurityBenefits_.allowance));
            }
            if (criteria.getTaxpayer() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTaxpayer(), SocialSecurityBenefits_.taxpayer));
            }
            if (criteria.getIsSelfVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsSelfVerify(), SocialSecurityBenefits_.isSelfVerify));
            }
            if (criteria.getIsHrVerify() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHrVerify(), SocialSecurityBenefits_.isHrVerify));
            }
            if (criteria.getPfTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getPfTypeId(),
                    root -> root.join(SocialSecurityBenefits_.pfType, JoinType.LEFT).get(EnumPfType_.id)));
            }
            if (criteria.getPfStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getPfStatusId(),
                    root -> root.join(SocialSecurityBenefits_.pfStatus, JoinType.LEFT).get(EnumPfStatus_.id)));
            }
            if (criteria.getProvidentPaySchemeId() != null) {
                specification = specification.and(buildSpecification(criteria.getProvidentPaySchemeId(),
                    root -> root.join(SocialSecurityBenefits_.providentPayScheme, JoinType.LEFT).get(EnumPfPayScheme_.id)));
            }
            if (criteria.getSocialSecurityPaySchemeId() != null) {
                specification = specification.and(buildSpecification(criteria.getSocialSecurityPaySchemeId(),
                    root -> root.join(SocialSecurityBenefits_.socialSecurityPayScheme, JoinType.LEFT).get(EnumSsPayScheme_.id)));
            }
            if (criteria.getSsStatusId() != null) {
                specification = specification.and(buildSpecification(criteria.getSsStatusId(),
                    root -> root.join(SocialSecurityBenefits_.ssStatus, JoinType.LEFT).get(EnumSsStatus_.id)));
            }
            if (criteria.getLaborTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getLaborTypeId(),
                    root -> root.join(SocialSecurityBenefits_.laborType, JoinType.LEFT).get(EnumEmpLaborType_.id)));
            }
            if (criteria.getTaxerTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTaxerTypeId(),
                    root -> root.join(SocialSecurityBenefits_.taxerType, JoinType.LEFT).get(EnumEmpTaxerType_.id)));
            }
            if (criteria.getTaxAreaId() != null) {
                specification = specification.and(buildSpecification(criteria.getTaxAreaId(),
                    root -> root.join(SocialSecurityBenefits_.taxArea, JoinType.LEFT).get(EnumEmpTaxArea_.id)));
            }
            if (criteria.getEmpId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmpId(),
                    root -> root.join(SocialSecurityBenefits_.emp, JoinType.LEFT).get(Employee_.id)));
            }
        }
        return specification;
    }
}
