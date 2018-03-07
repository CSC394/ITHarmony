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

import com.itharmony.domain.enumeration.UserType;

/**
 * A UserProfile.
 */
@Entity
@Table(name = "user_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "jhi_password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(name = "profile_picture_content_type")
    private String profilePictureContentType;

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

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidateEducation> candidateEducations = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<CandidateWorkExperience> candidateWorkExperiences = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SkillsProfile> skillsProfiles = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobPost> jobPosts = new HashSet<>();

    @OneToMany(mappedBy = "userProfile")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobMatch> jobMatches = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserType getUserType() {
        return userType;
    }

    public UserProfile userType(UserType userType) {
        this.userType = userType;
        return this;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public UserProfile email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public UserProfile password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public UserProfile phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public UserProfile city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public UserProfile state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public UserProfile profilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
        return this;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getProfilePictureContentType() {
        return profilePictureContentType;
    }

    public UserProfile profilePictureContentType(String profilePictureContentType) {
        this.profilePictureContentType = profilePictureContentType;
        return this;
    }

    public void setProfilePictureContentType(String profilePictureContentType) {
        this.profilePictureContentType = profilePictureContentType;
    }

    public String getBio() {
        return bio;
    }

    public UserProfile bio(String bio) {
        this.bio = bio;
        return this;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public CultureProfile getCultureProfile() {
        return cultureProfile;
    }

    public UserProfile cultureProfile(CultureProfile cultureProfile) {
        this.cultureProfile = cultureProfile;
        return this;
    }

    public void setCultureProfile(CultureProfile cultureProfile) {
        this.cultureProfile = cultureProfile;
    }

    public CandidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public UserProfile candidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
        return this;
    }

    public void setCandidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    public UserProfile companyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
        return this;
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public Set<CandidateEducation> getCandidateEducations() {
        return candidateEducations;
    }

    public UserProfile candidateEducations(Set<CandidateEducation> candidateEducations) {
        this.candidateEducations = candidateEducations;
        return this;
    }

    public UserProfile addCandidateEducation(CandidateEducation candidateEducation) {
        this.candidateEducations.add(candidateEducation);
        candidateEducation.setUserProfile(this);
        return this;
    }

    public UserProfile removeCandidateEducation(CandidateEducation candidateEducation) {
        this.candidateEducations.remove(candidateEducation);
        candidateEducation.setUserProfile(null);
        return this;
    }

    public void setCandidateEducations(Set<CandidateEducation> candidateEducations) {
        this.candidateEducations = candidateEducations;
    }

    public Set<CandidateWorkExperience> getCandidateWorkExperiences() {
        return candidateWorkExperiences;
    }

    public UserProfile candidateWorkExperiences(Set<CandidateWorkExperience> candidateWorkExperiences) {
        this.candidateWorkExperiences = candidateWorkExperiences;
        return this;
    }

    public UserProfile addCandidateWorkExperience(CandidateWorkExperience candidateWorkExperience) {
        this.candidateWorkExperiences.add(candidateWorkExperience);
        candidateWorkExperience.setUserProfile(this);
        return this;
    }

    public UserProfile removeCandidateWorkExperience(CandidateWorkExperience candidateWorkExperience) {
        this.candidateWorkExperiences.remove(candidateWorkExperience);
        candidateWorkExperience.setUserProfile(null);
        return this;
    }

    public void setCandidateWorkExperiences(Set<CandidateWorkExperience> candidateWorkExperiences) {
        this.candidateWorkExperiences = candidateWorkExperiences;
    }

    public Set<SkillsProfile> getSkillsProfiles() {
        return skillsProfiles;
    }

    public UserProfile skillsProfiles(Set<SkillsProfile> skillsProfiles) {
        this.skillsProfiles = skillsProfiles;
        return this;
    }

    public UserProfile addSkillsProfile(SkillsProfile skillsProfile) {
        this.skillsProfiles.add(skillsProfile);
        skillsProfile.setUserProfile(this);
        return this;
    }

    public UserProfile removeSkillsProfile(SkillsProfile skillsProfile) {
        this.skillsProfiles.remove(skillsProfile);
        skillsProfile.setUserProfile(null);
        return this;
    }

    public void setSkillsProfiles(Set<SkillsProfile> skillsProfiles) {
        this.skillsProfiles = skillsProfiles;
    }

    public Set<JobPost> getJobPosts() {
        return jobPosts;
    }

    public UserProfile jobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
        return this;
    }

    public UserProfile addJobPost(JobPost jobPost) {
        this.jobPosts.add(jobPost);
        jobPost.setUserProfile(this);
        return this;
    }

    public UserProfile removeJobPost(JobPost jobPost) {
        this.jobPosts.remove(jobPost);
        jobPost.setUserProfile(null);
        return this;
    }

    public void setJobPosts(Set<JobPost> jobPosts) {
        this.jobPosts = jobPosts;
    }

    public Set<JobMatch> getJobMatches() {
        return jobMatches;
    }

    public UserProfile jobMatches(Set<JobMatch> jobMatches) {
        this.jobMatches = jobMatches;
        return this;
    }

    public UserProfile addJobMatch(JobMatch jobMatch) {
        this.jobMatches.add(jobMatch);
        jobMatch.setUserProfile(this);
        return this;
    }

    public UserProfile removeJobMatch(JobMatch jobMatch) {
        this.jobMatches.remove(jobMatch);
        jobMatch.setUserProfile(null);
        return this;
    }

    public void setJobMatches(Set<JobMatch> jobMatches) {
        this.jobMatches = jobMatches;
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
        UserProfile userProfile = (UserProfile) o;
        if (userProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserProfile{" +
            "id=" + getId() +
            ", userType='" + getUserType() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", phone='" + getPhone() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", profilePicture='" + getProfilePicture() + "'" +
            ", profilePictureContentType='" + getProfilePictureContentType() + "'" +
            ", bio='" + getBio() + "'" +
            "}";
    }
}
