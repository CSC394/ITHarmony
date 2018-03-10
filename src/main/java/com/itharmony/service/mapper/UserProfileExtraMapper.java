package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.UserProfileExtraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserProfileExtra and its DTO UserProfileExtraDTO.
 */
@Mapper(componentModel = "spring", uses = {CultureProfileMapper.class, CandidateProfileMapper.class, CompanyProfileMapper.class})
public interface UserProfileExtraMapper extends EntityMapper<UserProfileExtraDTO, UserProfileExtra> {

    @Mapping(source = "cultureProfile.id", target = "cultureProfileId")
    @Mapping(source = "candidateProfile.id", target = "candidateProfileId")
    @Mapping(source = "companyProfile.id", target = "companyProfileId")
    UserProfileExtraDTO toDto(UserProfileExtra userProfileExtra);

    @Mapping(source = "cultureProfileId", target = "cultureProfile")
    @Mapping(source = "candidateProfileId", target = "candidateProfile")
    @Mapping(source = "companyProfileId", target = "companyProfile")
    @Mapping(target = "candidateEducations", ignore = true)
    @Mapping(target = "candidateWorkExperiences", ignore = true)
    @Mapping(target = "skillsProfiles", ignore = true)
    @Mapping(target = "jobPosts", ignore = true)
    @Mapping(target = "jobMatches", ignore = true)
    UserProfileExtra toEntity(UserProfileExtraDTO userProfileExtraDTO);

    default UserProfileExtra fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserProfileExtra userProfileExtra = new UserProfileExtra();
        userProfileExtra.setId(id);
        return userProfileExtra;
    }
}
