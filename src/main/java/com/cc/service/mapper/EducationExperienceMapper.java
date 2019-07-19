package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EducationExperienceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EducationExperience} and its DTO {@link EducationExperienceDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnumEducationMapper.class, EmployeeMapper.class})
public interface EducationExperienceMapper extends EntityMapper<EducationExperienceDTO, EducationExperience> {

    @Mapping(source = "education.id", target = "educationId")
    @Mapping(source = "emp.id", target = "empId")
    EducationExperienceDTO toDto(EducationExperience educationExperience);

    @Mapping(source = "educationId", target = "education")
    @Mapping(source = "empId", target = "emp")
    EducationExperience toEntity(EducationExperienceDTO educationExperienceDTO);

    default EducationExperience fromId(Long id) {
        if (id == null) {
            return null;
        }
        EducationExperience educationExperience = new EducationExperience();
        educationExperience.setId(id);
        return educationExperience;
    }
}
