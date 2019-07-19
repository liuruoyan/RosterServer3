package com.cc.repository;

import com.cc.domain.SocialSecurityBenefits;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SocialSecurityBenefits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SocialSecurityBenefitsRepository extends JpaRepository<SocialSecurityBenefits, Long>, JpaSpecificationExecutor<SocialSecurityBenefits> {

}
