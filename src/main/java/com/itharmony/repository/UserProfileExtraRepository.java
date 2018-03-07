package com.itharmony.repository;

import com.itharmony.domain.UserProfileExtra;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UserProfileExtra entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserProfileExtraRepository extends JpaRepository<UserProfileExtra, Long> {

}
