package com.itharmony.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;
import com.itharmony.domain.enumeration.JobType;

/**
 * A DTO for the CandidateProfile entity.
 */
public class CandidateProfileDTO implements Serializable {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private JobType desiredJobType;

    @NotNull
    private String email;

    private String linkedIn;

    private String website;

    private String github;

    @Lob
    private byte[] resume;
    private String resumeContentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JobType getDesiredJobType() {
        return desiredJobType;
    }

    public void setDesiredJobType(JobType desiredJobType) {
        this.desiredJobType = desiredJobType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public byte[] getResume() {
        return resume;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getResumeContentType() {
        return resumeContentType;
    }

    public void setResumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CandidateProfileDTO candidateProfileDTO = (CandidateProfileDTO) o;
        if(candidateProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateProfileDTO{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", desiredJobType='" + getDesiredJobType() + "'" +
            ", email='" + getEmail() + "'" +
            ", linkedIn='" + getLinkedIn() + "'" +
            ", website='" + getWebsite() + "'" +
            ", github='" + getGithub() + "'" +
            ", resume='" + getResume() + "'" +
            "}";
    }
}
