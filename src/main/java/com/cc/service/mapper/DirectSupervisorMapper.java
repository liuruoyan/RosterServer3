package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.DirectSupervisorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DirectSupervisor} and its DTO {@link DirectSupervisorDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface DirectSupervisorMapper extends EntityMapper<DirectSupervisorDTO, DirectSupervisor> {

    @Mapping(source = "emp.id", target = "empId")
    DirectSupervisorDTO toDto(DirectSupervisor directSupervisor);

    @Mapping(source = "empId", target = "emp")
    DirectSupervisor toEntity(DirectSupervisorDTO directSupervisorDTO);

    default DirectSupervisor fromId(Long id) {
        if (id == null) {
            return null;
        }
        DirectSupervisor directSupervisor = new DirectSupervisor();
        directSupervisor.setId(id);
        return directSupervisor;
    }
}
