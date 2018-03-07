package com.itharmony.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.itharmony.domain.enumeration.JobType;

/**
 * A CandidateProfile.
 */
@Entity
@Table(name = "candidate_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CandidateProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "desired_job_type", nullable = false)
    private JobType desiredJobType;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "linked_in")
    private String linkedIn;

    @Column(name = "website")
    private String website;

    @Column(name = "github")
    private String github;

    @Lob
    @Column(name = "resume")
    private byte[] resume;

    @Column(name = "resume_content_type")
    private String resumeContentType;

    @OneToOne(mappedBy = "candidateProfile")
    @JsonIgnore
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public CandidateProfile firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CandidateProfile lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public JobType getDesiredJobType() {
        return desiredJobType;
    }

    public CandidateProfile desiredJobType(JobType desiredJobType) {
        this.desiredJobType = desiredJobType;
        return this;
    }

    public void setDesiredJobType(JobType desiredJobType) {
        this.desiredJobType = desiredJobType;
    }

    public String getEmail() {
        return email;
    }

    public CandidateProfile email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public CandidateProfile linkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
        return this;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getWebsite() {
        return website;
    }

    public CandidateProfile website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getGithub() {
        return github;
    }

    public CandidateProfile github(String github) {
        this.github = github;
        return this;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public byte[] getResume() {
        return resume;
    }

    public CandidateProfile resume(byte[] resume) {
        this.resume = resume;
        return this;
    }

    public void setResume(byte[] resume) {
        this.resume = resume;
    }

    public String getResumeContentType() {
        return resumeContentType;
    }

    public CandidateProfile resumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
        return this;
    }

    public void setResumeContentType(String resumeContentType) {
        this.resumeContentType = resumeContentType;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public CandidateProfile userProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CandidateProfile candidateProfile = (CandidateProfile) o;
        if (candidateProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateProfile{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", desiredJobType='" + getDesiredJobType() + "'" +
            ", email='" + getEmail() + "'" +
            ", linkedIn='" + getLinkedIn() + "'" +
            ", website='" + getWebsite() + "'" +
            ", github='" + getGithub() + "'" +
            ", resume='" + getResume() + "'" +
            ", resumeContentType='" + getResumeContentType() + "'" +
            "}";
    }
}
