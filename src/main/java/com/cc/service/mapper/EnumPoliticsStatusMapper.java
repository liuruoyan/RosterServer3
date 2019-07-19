package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumPoliticsStatusDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumPoliticsStatus} and its DTO {@link EnumPoliticsStatusDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumPoliticsStatusMapper extends EntityMapper<EnumPoliticsStatusDTO, EnumPoliticsStatus> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumPoliticsStatusDTO toDto(EnumPoliticsStatus enumPoliticsStatus);

    @Mapping(source = "parentId", target = "parent")
    EnumPoliticsStatus toEntity(EnumPoliticsStatusDTO enumPoliticsStatusDTO);

    default EnumPoliticsStatus fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumPoliticsStatus enumPoliticsStatus = new EnumPoliticsStatus();
        enumPoliticsStatus.setId(id);
        return enumPoliticsStatus;
    }
}
