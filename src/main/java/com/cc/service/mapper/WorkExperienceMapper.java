package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.WorkExperienceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link WorkExperience} and its DTO {@link WorkExperienceDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface WorkExperienceMapper extends EntityMapper<WorkExperienceDTO, WorkExperience> {

    @Mapping(source = "emp.id", target = "empId")
    WorkExperienceDTO toDto(WorkExperience workExperience);

    @Mapping(source = "empId", target = "emp")
    WorkExperience toEntity(WorkExperienceDTO workExperienceDTO);

    default WorkExperience fromId(Long id) {
        if (id == null) {
            return null;
        }
        WorkExperience workExperience = new WorkExperience();
        workExperience.setId(id);
        return workExperience;
    }
}
