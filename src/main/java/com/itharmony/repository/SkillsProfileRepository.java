package com.itharmony.repository;

import com.itharmony.domain.SkillsProfile;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the SkillsProfile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillsProfileRepository extends JpaRepository<SkillsProfile, Long> {

}
