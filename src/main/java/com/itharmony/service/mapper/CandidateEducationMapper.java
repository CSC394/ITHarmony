package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CandidateEducationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CandidateEducation and its DTO CandidateEducationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileMapper.class})
public interface CandidateEducationMapper extends EntityMapper<CandidateEducationDTO, CandidateEducation> {

    @Mapping(source = "userProfile.id", target = "userProfileId")
    CandidateEducationDTO toDto(CandidateEducation candidateEducation);

    @Mapping(source = "userProfileId", target = "userProfile")
    CandidateEducation toEntity(CandidateEducationDTO candidateEducationDTO);

    default CandidateEducation fromId(Long id) {
        if (id == null) {
            return null;
        }
        CandidateEducation candidateEducation = new CandidateEducation();
        candidateEducation.setId(id);
        return candidateEducation;
    }
}
