package com.cc.repository;

import com.cc.domain.EnumMaritalStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumMaritalStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumMaritalStatusRepository extends JpaRepository<EnumMaritalStatus, Long>, JpaSpecificationExecutor<EnumMaritalStatus> {

}
