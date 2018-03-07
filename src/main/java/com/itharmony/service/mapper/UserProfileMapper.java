package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.UserProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UserProfile and its DTO UserProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {CultureProfileMapper.class, CandidateProfileMapper.class, CompanyProfileMapper.class})
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, UserProfile> {

    @Mapping(source = "cultureProfile.id", target = "cultureProfileId")
    @Mapping(source = "candidateProfile.id", target = "candidateProfileId")
    @Mapping(source = "companyProfile.id", target = "companyProfileId")
    UserProfileDTO toDto(UserProfile userProfile);

    @Mapping(source = "cultureProfileId", target = "cultureProfile")
    @Mapping(source = "candidateProfileId", target = "candidateProfile")
    @Mapping(source = "companyProfileId", target = "companyProfile")
    @Mapping(target = "candidateEducations", ignore = true)
    @Mapping(target = "candidateWorkExperiences", ignore = true)
    @Mapping(target = "skillsProfiles", ignore = true)
    @Mapping(target = "jobPosts", ignore = true)
    @Mapping(target = "jobMatches", ignore = true)
    UserProfile toEntity(UserProfileDTO userProfileDTO);

    default UserProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserProfile userProfile = new UserProfile();
        userProfile.setId(id);
        return userProfile;
    }
}
