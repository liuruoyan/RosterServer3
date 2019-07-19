package com.cc.repository;

import com.cc.domain.EnumSsStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumSsStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumSsStatusRepository extends JpaRepository<EnumSsStatus, Long>, JpaSpecificationExecutor<EnumSsStatus> {

}
