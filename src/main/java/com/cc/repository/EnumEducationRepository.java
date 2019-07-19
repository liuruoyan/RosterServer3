package com.cc.repository;

import com.cc.domain.EnumEducation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumEducation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumEducationRepository extends JpaRepository<EnumEducation, Long>, JpaSpecificationExecutor<EnumEducation> {

}
