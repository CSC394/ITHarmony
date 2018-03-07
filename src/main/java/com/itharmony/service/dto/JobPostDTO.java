package com.itharmony.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.itharmony.domain.enumeration.JobType;

/**
 * A DTO for the JobPost entity.
 */
public class JobPostDTO implements Serializable {

    private Long id;

    private String positionTitle;

    private JobType positionType;

    private String description;

    private String city;

    private String state;

    private LocalDate startDate;

    private Boolean degreeRequired;

    private Float minimumGPA;

    private Long skillsProfileId;

    private Long userProfileId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public JobType getPositionType() {
        return positionType;
    }

    public void setPositionType(JobType positionType) {
        this.positionType = positionType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Boolean isDegreeRequired() {
        return degreeRequired;
    }

    public void setDegreeRequired(Boolean degreeRequired) {
        this.degreeRequired = degreeRequired;
    }

    public Float getMinimumGPA() {
        return minimumGPA;
    }

    public void setMinimumGPA(Float minimumGPA) {
        this.minimumGPA = minimumGPA;
    }

    public Long getSkillsProfileId() {
        return skillsProfileId;
    }

    public void setSkillsProfileId(Long skillsProfileId) {
        this.skillsProfileId = skillsProfileId;
    }

    public Long getUserProfileId() {
        return userProfileId;
    }

    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobPostDTO jobPostDTO = (JobPostDTO) o;
        if(jobPostDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobPostDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobPostDTO{" +
            "id=" + getId() +
            ", positionTitle='" + getPositionTitle() + "'" +
            ", positionType='" + getPositionType() + "'" +
            ", description='" + getDescription() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", degreeRequired='" + isDegreeRequired() + "'" +
            ", minimumGPA=" + getMinimumGPA() +
            "}";
    }
}
