package com.itharmony.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import com.itharmony.domain.enumeration.UserTypeT;

/**
 * A DTO for the UserProfileExtra entity.
 */
public class UserProfileExtraDTO implements Serializable {

    private Long id;

    @NotNull
    private UserTypeT userTypeT;

    private String phone;

    private String city;

    private String state;

    private String bio;

    private Long cultureProfileId;

    private Long candidateProfileId;

    private Long companyProfileId;

    private Long userId;

    private String userLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserTypeT getUserTypeT() {
        return userTypeT;
    }

    public void setUserTypeT(UserTypeT userTypeT) {
        this.userTypeT = userTypeT;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Long getCultureProfileId() {
        return cultureProfileId;
    }

    public void setCultureProfileId(Long cultureProfileId) {
        this.cultureProfileId = cultureProfileId;
    }

    public Long getCandidateProfileId() {
        return candidateProfileId;
    }

    public void setCandidateProfileId(Long candidateProfileId) {
        this.candidateProfileId = candidateProfileId;
    }

    public Long getCompanyProfileId() {
        return companyProfileId;
    }

    public void setCompanyProfileId(Long companyProfileId) {
        this.companyProfileId = companyProfileId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserProfileExtraDTO userProfileExtraDTO = (UserProfileExtraDTO) o;
        if(userProfileExtraDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userProfileExtraDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserProfileExtraDTO{" +
            "id=" + getId() +
            ", userTypeT='" + getUserTypeT() + "'" +
            ", phone='" + getPhone() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", bio='" + getBio() + "'" +
            "}";
    }
}
