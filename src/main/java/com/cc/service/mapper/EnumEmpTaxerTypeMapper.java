package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumEmpTaxerTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumEmpTaxerType} and its DTO {@link EnumEmpTaxerTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumEmpTaxerTypeMapper extends EntityMapper<EnumEmpTaxerTypeDTO, EnumEmpTaxerType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumEmpTaxerTypeDTO toDto(EnumEmpTaxerType enumEmpTaxerType);

    @Mapping(source = "parentId", target = "parent")
    EnumEmpTaxerType toEntity(EnumEmpTaxerTypeDTO enumEmpTaxerTypeDTO);

    default EnumEmpTaxerType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumEmpTaxerType enumEmpTaxerType = new EnumEmpTaxerType();
        enumEmpTaxerType.setId(id);
        return enumEmpTaxerType;
    }
}
