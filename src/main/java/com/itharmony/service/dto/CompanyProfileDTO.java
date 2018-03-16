package com.itharmony.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CompanyProfile entity.
 */
public class CompanyProfileDTO implements Serializable {

    private Long id;

    @NotNull
    private String companyName;

    @NotNull
    private Integer postings;

    @NotNull
    private String email;

    private String industry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getPostings() {
        return postings;
    }

    public void setPostings(Integer postings) {
        this.postings = postings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CompanyProfileDTO companyProfileDTO = (CompanyProfileDTO) o;
        if(companyProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), companyProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CompanyProfileDTO{" +
            "id=" + getId() +
            ", companyName='" + getCompanyName() + "'" +
            ", postings=" + getPostings() +
            ", email='" + getEmail() + "'" +
            ", industry='" + getIndustry() + "'" +
            "}";
    }
}
