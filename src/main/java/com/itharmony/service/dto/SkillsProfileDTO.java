package com.itharmony.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.itharmony.domain.enumeration.Specialty;

/**
 * A DTO for the SkillsProfile entity.
 */
public class SkillsProfileDTO implements Serializable {

    private Long id;

    private Boolean forCandidate;

    private Specialty specialty;

    private Integer specialtyXP;

    private String skill1;

    private String skill2;

    private String skill3;

    private String skill4;

    private String skill5;

    private Integer skill1XP;

    private Integer skill2XP;

    private Integer skill3XP;

    private Integer skill4XP;

    private Integer skill5XP;

    private Long userProfileId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isForCandidate() {
        return forCandidate;
    }

    public void setForCandidate(Boolean forCandidate) {
        this.forCandidate = forCandidate;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Integer getSpecialtyXP() {
        return specialtyXP;
    }

    public void setSpecialtyXP(Integer specialtyXP) {
        this.specialtyXP = specialtyXP;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public Integer getSkill1XP() {
        return skill1XP;
    }

    public void setSkill1XP(Integer skill1XP) {
        this.skill1XP = skill1XP;
    }

    public Integer getSkill2XP() {
        return skill2XP;
    }

    public void setSkill2XP(Integer skill2XP) {
        this.skill2XP = skill2XP;
    }

    public Integer getSkill3XP() {
        return skill3XP;
    }

    public void setSkill3XP(Integer skill3XP) {
        this.skill3XP = skill3XP;
    }

    public Integer getSkill4XP() {
        return skill4XP;
    }

    public void setSkill4XP(Integer skill4XP) {
        this.skill4XP = skill4XP;
    }

    public Integer getSkill5XP() {
        return skill5XP;
    }

    public void setSkill5XP(Integer skill5XP) {
        this.skill5XP = skill5XP;
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

        SkillsProfileDTO skillsProfileDTO = (SkillsProfileDTO) o;
        if(skillsProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), skillsProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SkillsProfileDTO{" +
            "id=" + getId() +
            ", forCandidate='" + isForCandidate() + "'" +
            ", specialty='" + getSpecialty() + "'" +
            ", specialtyXP=" + getSpecialtyXP() +
            ", skill1='" + getSkill1() + "'" +
            ", skill2='" + getSkill2() + "'" +
            ", skill3='" + getSkill3() + "'" +
            ", skill4='" + getSkill4() + "'" +
            ", skill5='" + getSkill5() + "'" +
            ", skill1XP=" + getSkill1XP() +
            ", skill2XP=" + getSkill2XP() +
            ", skill3XP=" + getSkill3XP() +
            ", skill4XP=" + getSkill4XP() +
            ", skill5XP=" + getSkill5XP() +
            "}";
    }
}
