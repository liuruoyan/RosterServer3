package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumMaritalStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumMaritalStatus} and its DTO {@link EnumMaritalStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumMaritalStatusMapper extends EntityMapper<EnumMaritalStatusDTO, EnumMaritalStatus> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumMaritalStatusDTO toDto(EnumMaritalStatus enumMaritalStatus);

    @Mapping(source = "parentId", target = "parent")
    EnumMaritalStatus toEntity(EnumMaritalStatusDTO enumMaritalStatusDTO);

    default EnumMaritalStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumMaritalStatus enumMaritalStatus = new EnumMaritalStatus();
        enumMaritalStatus.setId(id);
        return enumMaritalStatus;
    }
}
