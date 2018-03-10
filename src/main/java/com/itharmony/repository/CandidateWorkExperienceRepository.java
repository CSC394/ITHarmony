package com.itharmony.repository;

import com.itharmony.domain.CandidateWorkExperience;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CandidateWorkExperience entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidateWorkExperienceRepository extends JpaRepository<CandidateWorkExperience, Long> {

}
