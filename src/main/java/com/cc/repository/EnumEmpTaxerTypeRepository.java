package com.cc.repository;

import com.cc.domain.EnumEmpTaxerType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumEmpTaxerType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumEmpTaxerTypeRepository extends JpaRepository<EnumEmpTaxerType, Long>, JpaSpecificationExecutor<EnumEmpTaxerType> {

}
