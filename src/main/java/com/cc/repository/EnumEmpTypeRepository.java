package com.cc.repository;

import com.cc.domain.EnumEmpType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumEmpType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumEmpTypeRepository extends JpaRepository<EnumEmpType, Long>, JpaSpecificationExecutor<EnumEmpType> {

}
