package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumPfStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumPfStatus} and its DTO {@link EnumPfStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumPfStatusMapper extends EntityMapper<EnumPfStatusDTO, EnumPfStatus> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumPfStatusDTO toDto(EnumPfStatus enumPfStatus);

    @Mapping(source = "parentId", target = "parent")
    EnumPfStatus toEntity(EnumPfStatusDTO enumPfStatusDTO);

    default EnumPfStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumPfStatus enumPfStatus = new EnumPfStatus();
        enumPfStatus.setId(id);
        return enumPfStatus;
    }
}
