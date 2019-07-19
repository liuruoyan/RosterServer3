package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumEmpLaborTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumEmpLaborType} and its DTO {@link EnumEmpLaborTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumEmpLaborTypeMapper extends EntityMapper<EnumEmpLaborTypeDTO, EnumEmpLaborType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumEmpLaborTypeDTO toDto(EnumEmpLaborType enumEmpLaborType);

    @Mapping(source = "parentId", target = "parent")
    EnumEmpLaborType toEntity(EnumEmpLaborTypeDTO enumEmpLaborTypeDTO);

    default EnumEmpLaborType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumEmpLaborType enumEmpLaborType = new EnumEmpLaborType();
        enumEmpLaborType.setId(id);
        return enumEmpLaborType;
    }
}
