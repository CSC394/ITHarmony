package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.SkillsProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SkillsProfile and its DTO SkillsProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileExtraMapper.class})
public interface SkillsProfileMapper extends EntityMapper<SkillsProfileDTO, SkillsProfile> {

    @Mapping(source = "userProfileExtra.id", target = "userProfileExtraId")
    SkillsProfileDTO toDto(SkillsProfile skillsProfile);

    @Mapping(target = "jobPost", ignore = true)
    @Mapping(source = "userProfileExtraId", target = "userProfileExtra")
    SkillsProfile toEntity(SkillsProfileDTO skillsProfileDTO);

    default SkillsProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        SkillsProfile skillsProfile = new SkillsProfile();
        skillsProfile.setId(id);
        return skillsProfile;
    }
}
