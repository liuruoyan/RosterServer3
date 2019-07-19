package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumSsPaySchemeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumSsPayScheme} and its DTO {@link EnumSsPaySchemeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumSsPaySchemeMapper extends EntityMapper<EnumSsPaySchemeDTO, EnumSsPayScheme> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumSsPaySchemeDTO toDto(EnumSsPayScheme enumSsPayScheme);

    @Mapping(source = "parentId", target = "parent")
    EnumSsPayScheme toEntity(EnumSsPaySchemeDTO enumSsPaySchemeDTO);

    default EnumSsPayScheme fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumSsPayScheme enumSsPayScheme = new EnumSsPayScheme();
        enumSsPayScheme.setId(id);
        return enumSsPayScheme;
    }
}
