package com.cc.repository;

import com.cc.domain.EnumPfType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumPfType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumPfTypeRepository extends JpaRepository<EnumPfType, Long>, JpaSpecificationExecutor<EnumPfType> {

}
