package com.cc.repository;

import com.cc.domain.PayCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PayCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayCardRepository extends JpaRepository<PayCard, Long>, JpaSpecificationExecutor<PayCard> {

}
