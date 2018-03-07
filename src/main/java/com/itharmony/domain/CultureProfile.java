package com.itharmony.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CultureProfile.
 */
@Entity
@Table(name = "culture_profile")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CultureProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dresscode")
    private Integer dresscode;

    @Column(name = "companysize")
    private Integer companysize;

    @Column(name = "floorplan")
    private Integer floorplan;

    @Column(name = "hours")
    private Integer hours;

    @Column(name = "jhi_groups")
    private Integer groups;

    @Column(name = "pace")
    private Integer pace;

    @Column(name = "rules")
    private Integer rules;

    @Column(name = "amenities")
    private Integer amenities;

    @Column(name = "meetings")
    private Integer meetings;

    @Column(name = "philanthropy")
    private Integer philanthropy;

    @Column(name = "outings")
    private Integer outings;

    @OneToOne(mappedBy = "cultureProfile")
    @JsonIgnore
    private UserProfile userProfile;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDresscode() {
        return dresscode;
    }

    public CultureProfile dresscode(Integer dresscode) {
        this.dresscode = dresscode;
        return this;
    }

    public void setDresscode(Integer dresscode) {
        this.dresscode = dresscode;
    }

    public Integer getCompanysize() {
        return companysize;
    }

    public CultureProfile companysize(Integer companysize) {
        this.companysize = companysize;
        return this;
    }

    public void setCompanysize(Integer companysize) {
        this.companysize = companysize;
    }

    public Integer getFloorplan() {
        return floorplan;
    }

    public CultureProfile floorplan(Integer floorplan) {
        this.floorplan = floorplan;
        return this;
    }

    public void setFloorplan(Integer floorplan) {
        this.floorplan = floorplan;
    }

    public Integer getHours() {
        return hours;
    }

    public CultureProfile hours(Integer hours) {
        this.hours = hours;
        return this;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getGroups() {
        return groups;
    }

    public CultureProfile groups(Integer groups) {
        this.groups = groups;
        return this;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getPace() {
        return pace;
    }

    public CultureProfile pace(Integer pace) {
        this.pace = pace;
        return this;
    }

    public void setPace(Integer pace) {
        this.pace = pace;
    }

    public Integer getRules() {
        return rules;
    }

    public CultureProfile rules(Integer rules) {
        this.rules = rules;
        return this;
    }

    public void setRules(Integer rules) {
        this.rules = rules;
    }

    public Integer getAmenities() {
        return amenities;
    }

    public CultureProfile amenities(Integer amenities) {
        this.amenities = amenities;
        return this;
    }

    public void setAmenities(Integer amenities) {
        this.amenities = amenities;
    }

    public Integer getMeetings() {
        return meetings;
    }

    public CultureProfile meetings(Integer meetings) {
        this.meetings = meetings;
        return this;
    }

    public void setMeetings(Integer meetings) {
        this.meetings = meetings;
    }

    public Integer getPhilanthropy() {
        return philanthropy;
    }

    public CultureProfile philanthropy(Integer philanthropy) {
        this.philanthropy = philanthropy;
        return this;
    }

    public void setPhilanthropy(Integer philanthropy) {
        this.philanthropy = philanthropy;
    }

    public Integer getOutings() {
        return outings;
    }

    public CultureProfile outings(Integer outings) {
        this.outings = outings;
        return this;
    }

    public void setOutings(Integer outings) {
        this.outings = outings;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public CultureProfile userProfile(UserProfile userProfile) {
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
        CultureProfile cultureProfile = (CultureProfile) o;
        if (cultureProfile.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cultureProfile.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CultureProfile{" +
            "id=" + getId() +
            ", dresscode=" + getDresscode() +
            ", companysize=" + getCompanysize() +
            ", floorplan=" + getFloorplan() +
            ", hours=" + getHours() +
            ", groups=" + getGroups() +
            ", pace=" + getPace() +
            ", rules=" + getRules() +
            ", amenities=" + getAmenities() +
            ", meetings=" + getMeetings() +
            ", philanthropy=" + getPhilanthropy() +
            ", outings=" + getOutings() +
            "}";
    }
}
