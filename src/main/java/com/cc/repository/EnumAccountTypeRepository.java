package com.cc.repository;

import com.cc.domain.EnumAccountType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumAccountType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumAccountTypeRepository extends JpaRepository<EnumAccountType, Long>, JpaSpecificationExecutor<EnumAccountType> {

}
