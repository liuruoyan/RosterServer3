package com.cc.repository;

import com.cc.domain.EnumPfPayScheme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumPfPayScheme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumPfPaySchemeRepository extends JpaRepository<EnumPfPayScheme, Long>, JpaSpecificationExecutor<EnumPfPayScheme> {

}
