package com.itharmony.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.itharmony.domain.enumeration.JobType;

/**
 * A JobPost.
 */
@Entity
@Table(name = "job_post")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobPost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position_title")
    private String positionTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "position_type")
    private JobType positionType;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "degree_required")
    private Boolean degreeRequired;

    @Column(name = "minimum_gpa")
    private Float minimumGPA;

    @OneToOne
    @JoinColumn(unique = true)
    private SkillsProfile skillsProfile;

    @OneToMany(mappedBy = "jobPost")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobMatch> jobMatches = new HashSet<>();

    @ManyToOne
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public JobPost positionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
        return this;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public JobType getPositionType() {
        return positionType;
    }

    public JobPost positionType(JobType positionType) {
        this.positionType = positionType;
        return this;
    }

    public void setPositionType(JobType positionType) {
        this.positionType = positionType;
    }

    public String getDescription() {
        return description;
    }

    public JobPost description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public JobPost city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public JobPost state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public JobPost startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Boolean isDegreeRequired() {
        return degreeRequired;
    }

    public JobPost degreeRequired(Boolean degreeRequired) {
        this.degreeRequired = degreeRequired;
        return this;
    }

    public void setDegreeRequired(Boolean degreeRequired) {
        this.degreeRequired = degreeRequired;
    }

    public Float getMinimumGPA() {
        return minimumGPA;
    }

    public JobPost minimumGPA(Float minimumGPA) {
        this.minimumGPA = minimumGPA;
        return this;
    }

    public void setMinimumGPA(Float minimumGPA) {
        this.minimumGPA = minimumGPA;
    }

    public SkillsProfile getSkillsProfile() {
        return skillsProfile;
    }

    public JobPost skillsProfile(SkillsProfile skillsProfile) {
        this.skillsProfile = skillsProfile;
        return this;
    }

    public void setSkillsProfile(SkillsProfile skillsProfile) {
        this.skillsProfile = skillsProfile;
    }

    public Set<JobMatch> getJobMatches() {
        return jobMatches;
    }

    public JobPost jobMatches(Set<JobMatch> jobMatches) {
        this.jobMatches = jobMatches;
        return this;
    }

    public JobPost addJobMatch(JobMatch jobMatch) {
        this.jobMatches.add(jobMatch);
        jobMatch.setJobPost(this);
        return this;
    }

    public JobPost removeJobMatch(JobMatch jobMatch) {
        this.jobMatches.remove(jobMatch);
        jobMatch.setJobPost(null);
        return this;
    }

    public void setJobMatches(Set<JobMatch> jobMatches) {
        this.jobMatches = jobMatches;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public JobPost userProfile(UserProfile userProfile) {
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
        JobPost jobPost = (JobPost) o;
        if (jobPost.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobPost.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobPost{" +
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
