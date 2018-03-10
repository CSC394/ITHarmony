package com.itharmony.repository;

import com.itharmony.domain.CandidateEducation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CandidateEducation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidateEducationRepository extends JpaRepository<CandidateEducation, Long> {

}
