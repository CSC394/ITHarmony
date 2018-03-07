package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.JobMatchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity JobMatch and its DTO JobMatchDTO.
 */
@Mapper(componentModel = "spring", uses = {UserProfileMapper.class, JobPostMapper.class})
public interface JobMatchMapper extends EntityMapper<JobMatchDTO, JobMatch> {

    @Mapping(source = "userProfile.id", target = "userProfileId")
    @Mapping(source = "jobPost.id", target = "jobPostId")
    JobMatchDTO toDto(JobMatch jobMatch);

    @Mapping(source = "userProfileId", target = "userProfile")
    @Mapping(source = "jobPostId", target = "jobPost")
    JobMatch toEntity(JobMatchDTO jobMatchDTO);

    default JobMatch fromId(Long id) {
        if (id == null) {
            return null;
        }
        JobMatch jobMatch = new JobMatch();
        jobMatch.setId(id);
        return jobMatch;
    }
}
