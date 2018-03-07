package com.itharmony.repository;

import com.itharmony.domain.CompanyProfile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CompanyProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {

}
