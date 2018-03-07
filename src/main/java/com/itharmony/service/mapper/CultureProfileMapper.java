package com.itharmony.service.mapper;

import com.itharmony.domain.*;
import com.itharmony.service.dto.CultureProfileDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CultureProfile and its DTO CultureProfileDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CultureProfileMapper extends EntityMapper<CultureProfileDTO, CultureProfile> {


    @Mapping(target = "userProfile", ignore = true)
    CultureProfile toEntity(CultureProfileDTO cultureProfileDTO);

    default CultureProfile fromId(Long id) {
        if (id == null) {
            return null;
        }
        CultureProfile cultureProfile = new CultureProfile();
        cultureProfile.setId(id);
        return cultureProfile;
    }
}
