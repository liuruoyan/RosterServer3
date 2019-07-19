package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumContractTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumContractType} and its DTO {@link EnumContractTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumContractTypeMapper extends EntityMapper<EnumContractTypeDTO, EnumContractType> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumContractTypeDTO toDto(EnumContractType enumContractType);

    @Mapping(source = "parentId", target = "parent")
    EnumContractType toEntity(EnumContractTypeDTO enumContractTypeDTO);

    default EnumContractType fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumContractType enumContractType = new EnumContractType();
        enumContractType.setId(id);
        return enumContractType;
    }
}
