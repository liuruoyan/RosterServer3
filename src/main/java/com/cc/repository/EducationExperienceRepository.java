package com.cc.repository;

import com.cc.domain.EducationExperience;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationExperience entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationExperienceRepository extends JpaRepository<EducationExperience, Long>, JpaSpecificationExecutor<EducationExperience> {

}
