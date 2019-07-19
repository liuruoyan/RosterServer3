package com.cc.repository;

import com.cc.domain.EnumEmpTaxArea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumEmpTaxArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumEmpTaxAreaRepository extends JpaRepository<EnumEmpTaxArea, Long>, JpaSpecificationExecutor<EnumEmpTaxArea> {

}
