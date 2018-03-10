package com.itharmony.repository;

import com.itharmony.domain.JobPost;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the JobPost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {

}
