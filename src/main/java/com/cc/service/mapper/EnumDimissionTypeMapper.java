package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumDimissionTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumDimissionType} and its DTO {@link EnumDimissionTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumDimissionTypeMapper extends EntityMapper<EnumDimissionTypeDTO, EnumDimissionType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumDimissionTypeDTO toDto(EnumDimissionType enumDimissionType);

    @Mapping(source = "parentId", target = "parent")
    EnumDimissionType toEntity(EnumDimissionTypeDTO enumDimissionTypeDTO);

    default EnumDimissionType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumDimissionType enumDimissionType = new EnumDimissionType();
        enumDimissionType.setId(id);
        return enumDimissionType;
    }
}
