package com.itharmony.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A CandidateWorkExperience.
 */
@Entity
@Table(name = "candidate_work_experience")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CandidateWorkExperience implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "current_job")
    private Boolean currentJob;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "description")
    private String description;

    @ManyToOne
    private UserProfileExtra userProfileExtra;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public CandidateWorkExperience companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPosition() {
        return position;
    }

    public CandidateWorkExperience position(String position) {
        this.position = position;
        return this;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Boolean isCurrentJob() {
        return currentJob;
    }

    public CandidateWorkExperience currentJob(Boolean currentJob) {
        this.currentJob = currentJob;
        return this;
    }

    public void setCurrentJob(Boolean currentJob) {
        this.currentJob = currentJob;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public CandidateWorkExperience startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public CandidateWorkExperience endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public CandidateWorkExperience description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserProfileExtra getUserProfileExtra() {
        return userProfileExtra;
    }

    public CandidateWorkExperience userProfileExtra(UserProfileExtra userProfileExtra) {
        this.userProfileExtra = userProfileExtra;
        return this;
    }

    public void setUserProfileExtra(UserProfileExtra userProfileExtra) {
        this.userProfileExtra = userProfileExtra;
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
        CandidateWorkExperience candidateWorkExperience = (CandidateWorkExperience) o;
        if (candidateWorkExperience.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateWorkExperience.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateWorkExperience{" +
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
