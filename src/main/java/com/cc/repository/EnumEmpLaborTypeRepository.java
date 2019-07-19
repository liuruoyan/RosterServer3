package com.cc.repository;

import com.cc.domain.EnumEmpLaborType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumEmpLaborType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumEmpLaborTypeRepository extends JpaRepository<EnumEmpLaborType, Long>, JpaSpecificationExecutor<EnumEmpLaborType> {

}
