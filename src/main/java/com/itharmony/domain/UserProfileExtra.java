package com.itharmony.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.itharmony.domain.enumeration.UserTypeT;

/**
 * A UserProfileExtra.
 */
@Entity
@Table(name = "user_profile_extra")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserProfileExtra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type_t", nullable = false)
    private UserTypeT userTypeT;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "bio")
    private String bio;

    @OneToOne
    @JoinColumn(unique = true)
    private CultureProfile cultureProfile;

    @OneToOne
    @JoinColumn(unique = true)
    private CandidateProfile candidateProfile;

    @OneToOne
    @JoinColumn(unique = true)
    private CompanyProfile companyProfile;

    @OneToMany(mappedBy = "userProfileExtra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidateEducation> candidateEducations = new HashSet<>();

    @OneToMany(mappedBy = "userProfileExtra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidateWorkExperience> candidateWorkExperiences = new HashSet<>();

    @OneToMany(mappedBy = "userProfileExtra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SkillsProfile> skillsProfiles = new HashSet<>();

    @OneToMany(mappedBy = "userProfileExtra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobPost> jobPosts = new HashSet<>();

    @OneToMany(mappedBy = "userProfileExtra")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobMatch> jobMatches = new HashSet<>();

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTypeT getUserTypeT() {
        return userTypeT;
    }

    public UserProfileExtra userTypeT(UserTypeT userTypeT) {
        this.userTypeT = userTypeT;
        return this;
    }

    public void setUserTypeT(UserTypeT userTypeT) {
        this.userTypeT = userTypeT;
    }

    public String getPhone() {
        return phone;
    }

    public UserProfileExtra phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public UserProfileExtra city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public UserProfileExtra state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBio() {
        return bio;
    }

    public UserProfileExtra bio(String bio) {
        this.bio = bio;
        return this;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public CultureProfile getCultureProfile() {
        return cultureProfile;
    }

    public UserProfileExtra cultureProfile(CultureProfile cultureProfile) {
        this.cultureProfile = cultureProfile;
        return this;
    }

    public void setCultureProfile(CultureProfile cultureProfile) {
        this.cultureProfile = cultureProfile;
    }

    public CandidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public UserProfileExtra candidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
        return this;
    }

    public void setCandidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public UserProfileExtra companyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
        return this;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public Set<CandidateEducation> getCandidateEducations() {
        return candidateEducations;
    }

    public UserProfileExtra candidateEducations(Set<CandidateEducation> candidateEducations) {
        this.candidateEducations = candidateEducations;
        return this;
    }

    public UserProfileExtra addCandidateEducation(CandidateEducation candidateEducation) {
        this.candidateEducations.add(candidateEducation);
        candidateEducation.setUserProfileExtra(this);
        return this;
    }

    public UserProfileExtra removeCandidateEducation(CandidateEducation candidateEducation) {
        this.candidateEducations.remove(candidateEducation);
        candidateEducation.setUserProfileExtra(null);
        return this;
    }

    public void setCandidateEducations(Set<CandidateEducation> candidateEducations) {
        this.candidateEducations = candidateEducations;
    }

    public Set<CandidateWorkExperience> getCandidateWorkExperiences() {
        return candidateWorkExperiences;
    }

    public UserProfileExtra candidateWorkExperiences(Set<CandidateWorkExperience> candidateWorkExperiences) {
        this.candidateWorkExperiences = candidateWorkExperiences;
        return this;
    }

    public UserProfileExtra addCandidateWorkExperience(CandidateWorkExperience candidateWorkExperience) {
        this.candidateWorkExperiences.add(candidateWorkExperience);
        candidateWorkExperience.setUserProfileExtra(this);
        return this;
    }

    public UserProfileExtra removeCandidateWorkExperience(CandidateWorkExperience candidateWorkExperience) {
        this.candidateWorkExperiences.remove(candidateWorkExperience);
        candidateWorkExperience.setUserProfileExtra(null);
        return this;
    }

    public void setCandidateWorkExperiences(Set<CandidateWorkExperience> candidateWorkExperiences) {
        this.candidateWorkExperiences = candidateWorkExperiences;
    }

    public Set<SkillsProfile> getSkillsProfiles() {
        return skillsProfiles;
    }

    public UserProfileExtra skillsProfiles(Set<SkillsProfile> skillsProfiles) {
        this.skillsProfiles = skillsProfiles;
        return this;
    }

    public UserProfileExtra addSkillsProfile(SkillsProfile skillsProfile) {
        this.skillsProfiles.add(skillsProfile);
        skillsProfile.setUserProfileExtra(this);
        return this;
    }

    public UserProfileExtra removeSkillsProfile(SkillsProfile skillsProfile) {
        this.skillsProfiles.remove(skillsProfile);
        skillsProfile.setUserProfileExtra(null);
        return this;
    }

    public void setSkillsProfiles(Set<SkillsProfile> skillsProfiles) {
        this.skillsProfiles = skillsProfiles;
    }

    public Set<JobPost> getJobPosts() {
        return jobPosts;
    }

    public UserProfileExtra jobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
        return this;
    }

    public UserProfileExtra addJobPost(JobPost jobPost) {
        this.jobPosts.add(jobPost);
        jobPost.setUserProfileExtra(this);
        return this;
    }

    public UserProfileExtra removeJobPost(JobPost jobPost) {
        this.jobPosts.remove(jobPost);
        jobPost.setUserProfileExtra(null);
        return this;
    }

    public void setJobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    public Set<JobMatch> getJobMatches() {
        return jobMatches;
    }

    public UserProfileExtra jobMatches(Set<JobMatch> jobMatches) {
        this.jobMatches = jobMatches;
        return this;
    }

    public UserProfileExtra addJobMatch(JobMatch jobMatch) {
        this.jobMatches.add(jobMatch);
        jobMatch.setUserProfileExtra(this);
        return this;
    }

    public UserProfileExtra removeJobMatch(JobMatch jobMatch) {
        this.jobMatches.remove(jobMatch);
        jobMatch.setUserProfileExtra(null);
        return this;
    }

    public void setJobMatches(Set<JobMatch> jobMatches) {
        this.jobMatches = jobMatches;
    }

    public User getUser() {
        return user;
    }

    public UserProfileExtra user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
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
        UserProfileExtra userProfileExtra = (UserProfileExtra) o;
        if (userProfileExtra.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userProfileExtra.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserProfileExtra{" +
            "id=" + getId() +
            ", userTypeT='" + getUserTypeT() + "'" +
            ", phone='" + getPhone() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", bio='" + getBio() + "'" +
            "}";
    }
}
