package com.itharmony.repository;

import com.itharmony.domain.CultureProfile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CultureProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CultureProfileRepository extends JpaRepository<CultureProfile, Long> {

}
