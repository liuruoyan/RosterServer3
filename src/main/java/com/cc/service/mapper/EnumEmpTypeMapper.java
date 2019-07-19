package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumEmpTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumEmpType} and its DTO {@link EnumEmpTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumEmpTypeMapper extends EntityMapper<EnumEmpTypeDTO, EnumEmpType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumEmpTypeDTO toDto(EnumEmpType enumEmpType);

    @Mapping(source = "parentId", target = "parent")
    EnumEmpType toEntity(EnumEmpTypeDTO enumEmpTypeDTO);

    default EnumEmpType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumEmpType enumEmpType = new EnumEmpType();
        enumEmpType.setId(id);
        return enumEmpType;
    }
}
