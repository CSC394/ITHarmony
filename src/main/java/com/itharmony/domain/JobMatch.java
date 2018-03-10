package com.itharmony.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A JobMatch.
 */
@Entity
@Table(name = "job_match")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "skill_rank")
    private Integer skillRank;

    @Column(name = "culture_rank")
    private Integer cultureRank;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "candidate_applied")
    private Boolean candidateApplied;

    @Column(name = "company_rejected")
    private Boolean companyRejected;

    @ManyToOne
    private UserProfileExtra userProfileExtra;

    @ManyToOne
    private JobPost jobPost;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSkillRank() {
        return skillRank;
    }

    public JobMatch skillRank(Integer skillRank) {
        this.skillRank = skillRank;
        return this;
    }

    public void setSkillRank(Integer skillRank) {
        this.skillRank = skillRank;
    }

    public Integer getCultureRank() {
        return cultureRank;
    }

    public JobMatch cultureRank(Integer cultureRank) {
        this.cultureRank = cultureRank;
        return this;
    }

    public void setCultureRank(Integer cultureRank) {
        this.cultureRank = cultureRank;
    }

    public Boolean isActive() {
        return active;
    }

    public JobMatch active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isCandidateApplied() {
        return candidateApplied;
    }

    public JobMatch candidateApplied(Boolean candidateApplied) {
        this.candidateApplied = candidateApplied;
        return this;
    }

    public void setCandidateApplied(Boolean candidateApplied) {
        this.candidateApplied = candidateApplied;
    }

    public Boolean isCompanyRejected() {
        return companyRejected;
    }

    public JobMatch companyRejected(Boolean companyRejected) {
        this.companyRejected = companyRejected;
        return this;
    }

    public void setCompanyRejected(Boolean companyRejected) {
        this.companyRejected = companyRejected;
    }

    public UserProfileExtra getUserProfileExtra() {
        return userProfileExtra;
    }

    public JobMatch userProfileExtra(UserProfileExtra userProfileExtra) {
        this.userProfileExtra = userProfileExtra;
        return this;
    }

    public void setUserProfileExtra(UserProfileExtra userProfileExtra) {
        this.userProfileExtra = userProfileExtra;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public JobMatch jobPost(JobPost jobPost) {
        this.jobPost = jobPost;
        return this;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
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
        JobMatch jobMatch = (JobMatch) o;
        if (jobMatch.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobMatch.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobMatch{" +
            "id=" + getId() +
            ", skillRank=" + getSkillRank() +
            ", cultureRank=" + getCultureRank() +
            ", active='" + isActive() + "'" +
            ", candidateApplied='" + isCandidateApplied() + "'" +
            ", companyRejected='" + isCompanyRejected() + "'" +
            "}";
    }
}
