package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumEmpStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumEmpStatus} and its DTO {@link EnumEmpStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumEmpStatusMapper extends EntityMapper<EnumEmpStatusDTO, EnumEmpStatus> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumEmpStatusDTO toDto(EnumEmpStatus enumEmpStatus);

    @Mapping(source = "parentId", target = "parent")
    EnumEmpStatus toEntity(EnumEmpStatusDTO enumEmpStatusDTO);

    default EnumEmpStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumEmpStatus enumEmpStatus = new EnumEmpStatus();
        enumEmpStatus.setId(id);
        return enumEmpStatus;
    }
}
