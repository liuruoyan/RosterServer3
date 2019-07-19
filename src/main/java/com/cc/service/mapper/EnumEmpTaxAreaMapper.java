package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumEmpTaxAreaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumEmpTaxArea} and its DTO {@link EnumEmpTaxAreaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumEmpTaxAreaMapper extends EntityMapper<EnumEmpTaxAreaDTO, EnumEmpTaxArea> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumEmpTaxAreaDTO toDto(EnumEmpTaxArea enumEmpTaxArea);

    @Mapping(source = "parentId", target = "parent")
    EnumEmpTaxArea toEntity(EnumEmpTaxAreaDTO enumEmpTaxAreaDTO);

    default EnumEmpTaxArea fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumEmpTaxArea enumEmpTaxArea = new EnumEmpTaxArea();
        enumEmpTaxArea.setId(id);
        return enumEmpTaxArea;
    }
}
