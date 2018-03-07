package com.itharmony.service.dto;


import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.itharmony.domain.enumeration.DegreeType;

/**
 * A DTO for the CandidateEducation entity.
 */
public class CandidateEducationDTO implements Serializable {

    private Long id;

    private String schoolName;

    private DegreeType degreeType;

    private Boolean inProgress;

    private LocalDate graduationDate;

    private String major;

    private Float gpa;

    private Long userProfileExtraId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public DegreeType getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(DegreeType degreeType) {
        this.degreeType = degreeType;
    }

    public Boolean isInProgress() {
        return inProgress;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Float getGpa() {
        return gpa;
    }

    public void setGpa(Float gpa) {
        this.gpa = gpa;
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

        CandidateEducationDTO candidateEducationDTO = (CandidateEducationDTO) o;
        if(candidateEducationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), candidateEducationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CandidateEducationDTO{" +
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
