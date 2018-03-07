package com.itharmony.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the CultureProfile entity.
 */
public class CultureProfileDTO implements Serializable {

    private Long id;

    private Integer dresscode;

    private Integer companysize;

    private Integer floorplan;

    private Integer hours;

    private Integer groups;

    private Integer pace;

    private Integer rules;

    private Integer amenities;

    private Integer meetings;

    private Integer philanthropy;

    private Integer outings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDresscode() {
        return dresscode;
    }

    public void setDresscode(Integer dresscode) {
        this.dresscode = dresscode;
    }

    public Integer getCompanysize() {
        return companysize;
    }

    public void setCompanysize(Integer companysize) {
        this.companysize = companysize;
    }

    public Integer getFloorplan() {
        return floorplan;
    }

    public void setFloorplan(Integer floorplan) {
        this.floorplan = floorplan;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getGroups() {
        return groups;
    }

    public void setGroups(Integer groups) {
        this.groups = groups;
    }

    public Integer getPace() {
        return pace;
    }

    public void setPace(Integer pace) {
        this.pace = pace;
    }

    public Integer getRules() {
        return rules;
    }

    public void setRules(Integer rules) {
        this.rules = rules;
    }

    public Integer getAmenities() {
        return amenities;
    }

    public void setAmenities(Integer amenities) {
        this.amenities = amenities;
    }

    public Integer getMeetings() {
        return meetings;
    }

    public void setMeetings(Integer meetings) {
        this.meetings = meetings;
    }

    public Integer getPhilanthropy() {
        return philanthropy;
    }

    public void setPhilanthropy(Integer philanthropy) {
        this.philanthropy = philanthropy;
    }

    public Integer getOutings() {
        return outings;
    }

    public void setOutings(Integer outings) {
        this.outings = outings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CultureProfileDTO cultureProfileDTO = (CultureProfileDTO) o;
        if(cultureProfileDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), cultureProfileDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CultureProfileDTO{" +
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
