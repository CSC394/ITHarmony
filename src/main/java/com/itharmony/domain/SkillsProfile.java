package com.itharmony.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import com.itharmony.domain.enumeration.Specialty;

/**
 * A SkillsProfile.
 */
@Entity
@Table(name = "skills_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SkillsProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "for_candidate")
    private Boolean forCandidate;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialty")
    private Specialty specialty;

    @Column(name = "specialty_xp")
    private Integer specialtyXP;

    @Column(name = "skill_1")
    private String skill1;

    @Column(name = "skill_2")
    private String skill2;

    @Column(name = "skill_3")
    private String skill3;

    @Column(name = "skill_4")
    private String skill4;

    @Column(name = "skill_5")
    private String skill5;

    @Column(name = "skill_1_xp")
    private Integer skill1XP;

    @Column(name = "skill_2_xp")
    private Integer skill2XP;

    @Column(name = "skill_3_xp")
    private Integer skill3XP;

    @Column(name = "skill_4_xp")
    private Integer skill4XP;

    @Column(name = "skill_5_xp")
    private Integer skill5XP;

    @OneToOne(mappedBy = "skillsProfile")
    @JsonIgnore
    private JobPost jobPost;

    @ManyToOne
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isForCandidate() {
        return forCandidate;
    }

    public SkillsProfile forCandidate(Boolean forCandidate) {
        this.forCandidate = forCandidate;
        return this;
    }

    public void setForCandidate(Boolean forCandidate) {
        this.forCandidate = forCandidate;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public SkillsProfile specialty(Specialty specialty) {
        this.specialty = specialty;
        return this;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Integer getSpecialtyXP() {
        return specialtyXP;
    }

    public SkillsProfile specialtyXP(Integer specialtyXP) {
        this.specialtyXP = specialtyXP;
        return this;
    }

    public void setSpecialtyXP(Integer specialtyXP) {
        this.specialtyXP = specialtyXP;
    }

    public String getSkill1() {
        return skill1;
    }

    public SkillsProfile skill1(String skill1) {
        this.skill1 = skill1;
        return this;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public SkillsProfile skill2(String skill2) {
        this.skill2 = skill2;
        return this;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public SkillsProfile skill3(String skill3) {
        this.skill3 = skill3;
        return this;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public SkillsProfile skill4(String skill4) {
        this.skill4 = skill4;
        return this;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public SkillsProfile skill5(String skill5) {
        this.skill5 = skill5;
        return this;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public Integer getSkill1XP() {
        return skill1XP;
    }

    public SkillsProfile skill1XP(Integer skill1XP) {
        this.skill1XP = skill1XP;
        return this;
    }

    public void setSkill1XP(Integer skill1XP) {
        this.skill1XP = skill1XP;
    }

    public Integer getSkill2XP() {
        return skill2XP;
    }

    public SkillsProfile skill2XP(Integer skill2XP) {
        this.skill2XP = skill2XP;
        return this;
    }

    public void setSkill2XP(Integer skill2XP) {
        this.skill2XP = skill2XP;
    }

    public Integer getSkill3XP() {
        return skill3XP;
    }

    public SkillsProfile skill3XP(Integer skill3XP) {
        this.skill3XP = skill3XP;
        return this;
    }

    public void setSkill3XP(Integer skill3XP) {
        this.skill3XP = skill3XP;
    }

    public Integer getSkill4XP() {
        return skill4XP;
    }

    public SkillsProfile skill4XP(Integer skill4XP) {
        this.skill4XP = skill4XP;
        return this;
    }

    public void setSkill4XP(Integer skill4XP) {
        this.skill4XP = skill4XP;
    }

    public Integer getSkill5XP() {
        return skill5XP;
    }

    public SkillsProfile skill5XP(Integer skill5XP) {
        this.skill5XP = skill5XP;
        return this;
    }

    public void setSkill5XP(Integer skill5XP) {
        this.skill5XP = skill5XP;
    }

    public JobPost getJobPost() {
        return jobPost;
    }

    public SkillsProfile jobPost(JobPost jobPost) {
        this.jobPost = jobPost;
        return this;
    }

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public SkillsProfile userProfile(UserProfile userProfile) {
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
        SkillsProfile skillsProfile = (SkillsProfile) o;
        if (skillsProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), skillsProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SkillsProfile{" +
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
