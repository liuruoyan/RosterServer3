package com.cc.repository;

import com.cc.domain.EnumHighestEducation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumHighestEducation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumHighestEducationRepository extends JpaRepository<EnumHighestEducation, Long>, JpaSpecificationExecutor<EnumHighestEducation> {

}
