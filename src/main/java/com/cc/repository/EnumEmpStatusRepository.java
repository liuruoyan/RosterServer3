package com.cc.repository;

import com.cc.domain.EnumEmpStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumEmpStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumEmpStatusRepository extends JpaRepository<EnumEmpStatus, Long>, JpaSpecificationExecutor<EnumEmpStatus> {

}
