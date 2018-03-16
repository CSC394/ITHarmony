package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CompanyProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CompanyProfile and its DTO CompanyProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CompanyProfileMapper extends EntityMapper<CompanyProfileDTO, CompanyProfile> {


    @Mapping(target = "userProfileExtra", ignore = true)
    CompanyProfile toEntity(CompanyProfileDTO companyProfileDTO);

    default CompanyProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompanyProfile companyProfile = new CompanyProfile();
        companyProfile.setId(id);
        return companyProfile;
    }
}
