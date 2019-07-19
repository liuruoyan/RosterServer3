package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumPfTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumPfType} and its DTO {@link EnumPfTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumPfTypeMapper extends EntityMapper<EnumPfTypeDTO, EnumPfType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumPfTypeDTO toDto(EnumPfType enumPfType);

    @Mapping(source = "parentId", target = "parent")
    EnumPfType toEntity(EnumPfTypeDTO enumPfTypeDTO);

    default EnumPfType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumPfType enumPfType = new EnumPfType();
        enumPfType.setId(id);
        return enumPfType;
    }
}
