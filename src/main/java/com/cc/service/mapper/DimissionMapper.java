package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.DimissionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Dimission} and its DTO {@link DimissionDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnumDimissionTypeMapper.class, EmployeeMapper.class})
public interface DimissionMapper extends EntityMapper<DimissionDTO, Dimission> {

    @Mapping(source = "dimissionType.id", target = "dimissionTypeId")
    @Mapping(source = "emp.id", target = "empId")
    DimissionDTO toDto(Dimission dimission);

    @Mapping(source = "dimissionTypeId", target = "dimissionType")
    @Mapping(source = "empId", target = "emp")
    Dimission toEntity(DimissionDTO dimissionDTO);

    default Dimission fromId(Long id) {
        if (id == null) {
            return null;
        }
        Dimission dimission = new Dimission();
        dimission.setId(id);
        return dimission;
    }
}
