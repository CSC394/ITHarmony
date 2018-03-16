package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CandidateProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CandidateProfile and its DTO CandidateProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CandidateProfileMapper extends EntityMapper<CandidateProfileDTO, CandidateProfile> {


    @Mapping(target = "userProfileExtra", ignore = true)
    CandidateProfile toEntity(CandidateProfileDTO candidateProfileDTO);

    default CandidateProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        CandidateProfile candidateProfile = new CandidateProfile();
        candidateProfile.setId(id);
        return candidateProfile;
    }
}
