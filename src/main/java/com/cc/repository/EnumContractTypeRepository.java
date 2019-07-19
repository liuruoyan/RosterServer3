package com.cc.repository;

import com.cc.domain.EnumContractType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumContractType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumContractTypeRepository extends JpaRepository<EnumContractType, Long>, JpaSpecificationExecutor<EnumContractType> {

}
