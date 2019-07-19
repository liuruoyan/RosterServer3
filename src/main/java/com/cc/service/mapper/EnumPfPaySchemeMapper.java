package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumPfPaySchemeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumPfPayScheme} and its DTO {@link EnumPfPaySchemeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumPfPaySchemeMapper extends EntityMapper<EnumPfPaySchemeDTO, EnumPfPayScheme> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumPfPaySchemeDTO toDto(EnumPfPayScheme enumPfPayScheme);

    @Mapping(source = "parentId", target = "parent")
    EnumPfPayScheme toEntity(EnumPfPaySchemeDTO enumPfPaySchemeDTO);

    default EnumPfPayScheme fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumPfPayScheme enumPfPayScheme = new EnumPfPayScheme();
        enumPfPayScheme.setId(id);
        return enumPfPayScheme;
    }
}
