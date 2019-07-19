package com.cc.repository;

import com.cc.domain.EnumDimissionType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumDimissionType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumDimissionTypeRepository extends JpaRepository<EnumDimissionType, Long>, JpaSpecificationExecutor<EnumDimissionType> {

}
