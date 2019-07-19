package com.cc.repository;

import com.cc.domain.EnumPoliticsStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumPoliticsStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumPoliticsStatusRepository extends JpaRepository<EnumPoliticsStatus, Long>, JpaSpecificationExecutor<EnumPoliticsStatus> {

}
