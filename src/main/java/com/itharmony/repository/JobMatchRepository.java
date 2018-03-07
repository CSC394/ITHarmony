package com.itharmony.repository;

import com.itharmony.domain.JobMatch;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the JobMatch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobMatchRepository extends JpaRepository<JobMatch, Long> {

}
