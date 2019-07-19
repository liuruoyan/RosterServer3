package com.cc.repository;

import com.cc.domain.Dimission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Dimission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DimissionRepository extends JpaRepository<Dimission, Long>, JpaSpecificationExecutor<Dimission> {

}
