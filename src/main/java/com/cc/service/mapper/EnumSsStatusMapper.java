package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumSsStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumSsStatus} and its DTO {@link EnumSsStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumSsStatusMapper extends EntityMapper<EnumSsStatusDTO, EnumSsStatus> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumSsStatusDTO toDto(EnumSsStatus enumSsStatus);

    @Mapping(source = "parentId", target = "parent")
    EnumSsStatus toEntity(EnumSsStatusDTO enumSsStatusDTO);

    default EnumSsStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumSsStatus enumSsStatus = new EnumSsStatus();
        enumSsStatus.setId(id);
        return enumSsStatus;
    }
}
