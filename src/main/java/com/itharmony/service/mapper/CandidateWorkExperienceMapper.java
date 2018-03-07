package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CandidateWorkExperienceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CandidateWorkExperience and its DTO CandidateWorkExperienceDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileMapper.class})
public interface CandidateWorkExperienceMapper extends EntityMapper<CandidateWorkExperienceDTO, CandidateWorkExperience> {

    @Mapping(source = "userProfile.id", target = "userProfileId")
    CandidateWorkExperienceDTO toDto(CandidateWorkExperience candidateWorkExperience);

    @Mapping(source = "userProfileId", target = "userProfile")
    CandidateWorkExperience toEntity(CandidateWorkExperienceDTO candidateWorkExperienceDTO);

    default CandidateWorkExperience fromId(Long id) {
        if (id == null) {
            return null;
        }
        CandidateWorkExperience candidateWorkExperience = new CandidateWorkExperience();
        candidateWorkExperience.setId(id);
        return candidateWorkExperience;
    }
}
