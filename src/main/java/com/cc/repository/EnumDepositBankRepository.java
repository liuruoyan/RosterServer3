package com.cc.repository;

import com.cc.domain.EnumDepositBank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EnumDepositBank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EnumDepositBankRepository extends JpaRepository<EnumDepositBank, Long>, JpaSpecificationExecutor<EnumDepositBank> {

}
