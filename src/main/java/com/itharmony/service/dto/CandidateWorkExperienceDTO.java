package com.itharmony.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CandidateWorkExperience entity.
 */
public class CandidateWorkExperienceDTO implements Serializable {

    private Long id;

    private String companyName;

    private String position;

    private Boolean currentJob;

    private LocalDate startDate;

    private LocalDate endDate;

    private String description;

    private Long userProfileExtraId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean isCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Boolean currentJob) {
        this.currentJob = currentJob;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserProfileExtraId() {
        return userProfileExtraId;
    }

    public void setUserProfileExtraId(Long userProfileExtraId) {
        this.userProfileExtraId = userProfileExtraId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidateWorkExperienceDTO candidateWorkExperienceDTO = (CandidateWorkExperienceDTO) o;
        if(candidateWorkExperienceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateWorkExperienceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateWorkExperienceDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", position='" + getPosition() + "'" +
            ", currentJob='" + isCurrentJob() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
