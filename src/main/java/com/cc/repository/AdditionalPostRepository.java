package com.cc.repository;

import com.cc.domain.AdditionalPost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdditionalPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdditionalPostRepository extends JpaRepository<AdditionalPost, Long>, JpaSpecificationExecutor<AdditionalPost> {

}
