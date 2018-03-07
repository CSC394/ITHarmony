package com.itharmony.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the JobMatch entity.
 */
public class JobMatchDTO implements Serializable {

    private Long id;

    private Integer skillRank;

    private Integer cultureRank;

    private Boolean active;

    private Boolean candidateApplied;

    private Boolean companyRejected;

    private Long userProfileId;

    private Long jobPostId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSkillRank() {
        return skillRank;
    }

    public void setSkillRank(Integer skillRank) {
        this.skillRank = skillRank;
    }

    public Integer getCultureRank() {
        return cultureRank;
    }

    public void setCultureRank(Integer cultureRank) {
        this.cultureRank = cultureRank;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isCandidateApplied() {
        return candidateApplied;
    }

    public void setCandidateApplied(Boolean candidateApplied) {
        this.candidateApplied = candidateApplied;
    }

    public Boolean isCompanyRejected() {
        return companyRejected;
    }

    public void setCompanyRejected(Boolean companyRejected) {
        this.companyRejected = companyRejected;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobMatchDTO jobMatchDTO = (JobMatchDTO) o;
        if(jobMatchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobMatchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobMatchDTO{" +
            "id=" + getId() +
            ", skillRank=" + getSkillRank() +
            ", cultureRank=" + getCultureRank() +
            ", active='" + isActive() + "'" +
            ", candidateApplied='" + isCandidateApplied() + "'" +
            ", companyRejected='" + isCompanyRejected() + "'" +
            "}";
    }
}
