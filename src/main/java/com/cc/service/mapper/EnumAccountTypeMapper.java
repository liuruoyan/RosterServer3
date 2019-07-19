package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumAccountTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumAccountType} and its DTO {@link EnumAccountTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumAccountTypeMapper extends EntityMapper<EnumAccountTypeDTO, EnumAccountType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumAccountTypeDTO toDto(EnumAccountType enumAccountType);

    @Mapping(source = "parentId", target = "parent")
    EnumAccountType toEntity(EnumAccountTypeDTO enumAccountTypeDTO);

    default EnumAccountType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumAccountType enumAccountType = new EnumAccountType();
        enumAccountType.setId(id);
        return enumAccountType;
    }
}
