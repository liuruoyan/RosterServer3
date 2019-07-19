package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumHighestEducationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumHighestEducation} and its DTO {@link EnumHighestEducationDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumHighestEducationMapper extends EntityMapper<EnumHighestEducationDTO, EnumHighestEducation> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumHighestEducationDTO toDto(EnumHighestEducation enumHighestEducation);

    @Mapping(source = "parentId", target = "parent")
    EnumHighestEducation toEntity(EnumHighestEducationDTO enumHighestEducationDTO);

    default EnumHighestEducation fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumHighestEducation enumHighestEducation = new EnumHighestEducation();
        enumHighestEducation.setId(id);
        return enumHighestEducation;
    }
}
