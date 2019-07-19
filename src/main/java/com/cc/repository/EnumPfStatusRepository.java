package com.cc.repository;

import com.cc.domain.EnumPfStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumPfStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumPfStatusRepository extends JpaRepository<EnumPfStatus, Long>, JpaSpecificationExecutor<EnumPfStatus> {

}
