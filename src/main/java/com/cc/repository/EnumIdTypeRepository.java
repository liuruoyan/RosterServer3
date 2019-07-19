package com.cc.repository;

import com.cc.domain.EnumIdType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumIdType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumIdTypeRepository extends JpaRepository<EnumIdType, Long>, JpaSpecificationExecutor<EnumIdType> {

}
