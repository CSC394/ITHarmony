package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CandidateWorkExperienceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CandidateWorkExperience and its DTO CandidateWorkExperienceDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileExtraMapper.class})
public interface CandidateWorkExperienceMapper extends EntityMapper<CandidateWorkExperienceDTO, CandidateWorkExperience> {

    @Mapping(source = "userProfileExtra.id", target = "userProfileExtraId")
    CandidateWorkExperienceDTO toDto(CandidateWorkExperience candidateWorkExperience);

    @Mapping(source = "userProfileExtraId", target = "userProfileExtra")
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
