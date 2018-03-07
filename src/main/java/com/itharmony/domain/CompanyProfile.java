package com.itharmony.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CompanyProfile.
 */
@Entity
@Table(name = "company_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompanyProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "company_name", nullable = false)
    private String companyName;

    @NotNull
    @Column(name = "postings", nullable = false)
    private Integer postings;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "industry")
    private String industry;

    @OneToOne(mappedBy = "companyProfile")
    @JsonIgnore
    private UserProfile userProfile;

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

    public CompanyProfile companyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getPostings() {
        return postings;
    }

    public CompanyProfile postings(Integer postings) {
        this.postings = postings;
        return this;
    }

    public void setPostings(Integer postings) {
        this.postings = postings;
    }

    public String getEmail() {
        return email;
    }

    public CompanyProfile email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustry() {
        return industry;
    }

    public CompanyProfile industry(String industry) {
        this.industry = industry;
        return this;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public CompanyProfile userProfile(UserProfile userProfile) {
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
        CompanyProfile companyProfile = (CompanyProfile) o;
        if (companyProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyProfile{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", postings=" + getPostings() +
            ", email='" + getEmail() + "'" +
            ", industry='" + getIndustry() + "'" +
            "}";
    }
}
