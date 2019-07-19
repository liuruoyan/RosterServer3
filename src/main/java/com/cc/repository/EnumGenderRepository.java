package com.cc.repository;

import com.cc.domain.EnumGender;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumGender entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumGenderRepository extends JpaRepository<EnumGender, Long>, JpaSpecificationExecutor<EnumGender> {

}
