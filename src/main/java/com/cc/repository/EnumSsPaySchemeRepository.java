package com.cc.repository;

import com.cc.domain.EnumSsPayScheme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumSsPayScheme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumSsPaySchemeRepository extends JpaRepository<EnumSsPayScheme, Long>, JpaSpecificationExecutor<EnumSsPayScheme> {

}
