package com.itharmony.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.itharmony.domain.enumeration.DegreeType;

/**
 * A CandidateEducation.
 */
@Entity
@Table(name = "candidate_education")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CandidateEducation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "school_name")
    private String schoolName;

    @Enumerated(EnumType.STRING)
    @Column(name = "degree_type")
    private DegreeType degreeType;

    @Column(name = "in_progress")
    private Boolean inProgress;

    @Column(name = "graduation_date")
    private LocalDate graduationDate;

    @Column(name = "major")
    private String major;

    @Column(name = "gpa")
    private Float gpa;

    @ManyToOne
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public CandidateEducation schoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public CandidateEducation degreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
        return this;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Boolean isInProgress() {
        return inProgress;
    }

    public CandidateEducation inProgress(Boolean inProgress) {
        this.inProgress = inProgress;
        return this;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public CandidateEducation graduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
        return this;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getMajor() {
        return major;
    }

    public CandidateEducation major(String major) {
        this.major = major;
        return this;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Float getGpa() {
        return gpa;
    }

    public CandidateEducation gpa(Float gpa) {
        this.gpa = gpa;
        return this;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public CandidateEducation userProfile(UserProfile userProfile) {
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
        CandidateEducation candidateEducation = (CandidateEducation) o;
        if (candidateEducation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateEducation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateEducation{" +
            "id=" + getId() +
            ", schoolName='" + getSchoolName() + "'" +
            ", degreeType='" + getDegreeType() + "'" +
            ", inProgress='" + isInProgress() + "'" +
            ", graduationDate='" + getGraduationDate() + "'" +
            ", major='" + getMajor() + "'" +
            ", gpa=" + getGpa() +
            "}";
    }
}
