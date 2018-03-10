package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.JobPostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobPost and its DTO JobPostDTO.
 */
@Mapper(componentModel = "spring", uses = {SkillsProfileMapper.class, UserProfileExtraMapper.class})
public interface JobPostMapper extends EntityMapper<JobPostDTO, JobPost> {

    @Mapping(source = "skillsProfile.id", target = "skillsProfileId")
    @Mapping(source = "userProfileExtra.id", target = "userProfileExtraId")
    JobPostDTO toDto(JobPost jobPost);

    @Mapping(source = "skillsProfileId", target = "skillsProfile")
    @Mapping(target = "jobMatches", ignore = true)
    @Mapping(source = "userProfileExtraId", target = "userProfileExtra")
    JobPost toEntity(JobPostDTO jobPostDTO);

    default JobPost fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobPost jobPost = new JobPost();
        jobPost.setId(id);
        return jobPost;
    }
}
