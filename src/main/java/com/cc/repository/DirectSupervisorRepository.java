package com.cc.repository;

import com.cc.domain.DirectSupervisor;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DirectSupervisor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DirectSupervisorRepository extends JpaRepository<DirectSupervisor, Long>, JpaSpecificationExecutor<DirectSupervisor> {

}
