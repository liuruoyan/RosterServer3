package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumIdTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumIdType} and its DTO {@link EnumIdTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumIdTypeMapper extends EntityMapper<EnumIdTypeDTO, EnumIdType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumIdTypeDTO toDto(EnumIdType enumIdType);

    @Mapping(source = "parentId", target = "parent")
    EnumIdType toEntity(EnumIdTypeDTO enumIdTypeDTO);

    default EnumIdType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumIdType enumIdType = new EnumIdType();
        enumIdType.setId(id);
        return enumIdType;
    }
}
