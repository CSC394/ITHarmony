package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CandidateEducationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CandidateEducation and its DTO CandidateEducationDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileExtraMapper.class})
public interface CandidateEducationMapper extends EntityMapper<CandidateEducationDTO, CandidateEducation> {

    @Mapping(source = "userProfileExtra.id", target = "userProfileExtraId")
    CandidateEducationDTO toDto(CandidateEducation candidateEducation);

    @Mapping(source = "userProfileExtraId", target = "userProfileExtra")
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
