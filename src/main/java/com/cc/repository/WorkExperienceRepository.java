package com.cc.repository;

import com.cc.domain.WorkExperience;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkExperience entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Long>, JpaSpecificationExecutor<WorkExperience> {

}
