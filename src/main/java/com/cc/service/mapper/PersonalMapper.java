package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.PersonalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Personal} and its DTO {@link PersonalDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnumAccountTypeMapper.class, EnumHighestEducationMapper.class, EnumPoliticsStatusMapper.class, EnumMaritalStatusMapper.class, EmployeeMapper.class})
public interface PersonalMapper extends EntityMapper<PersonalDTO, Personal> {

    @Mapping(source = "accountType.id", target = "accountTypeId")
    @Mapping(source = "highestEducation.id", target = "highestEducationId")
    @Mapping(source = "politicsStatus.id", target = "politicsStatusId")
    @Mapping(source = "maritalStatus.id", target = "maritalStatusId")
    @Mapping(source = "emp.id", target = "empId")
    PersonalDTO toDto(Personal personal);

    @Mapping(source = "accountTypeId", target = "accountType")
    @Mapping(source = "highestEducationId", target = "highestEducation")
    @Mapping(source = "politicsStatusId", target = "politicsStatus")
    @Mapping(source = "maritalStatusId", target = "maritalStatus")
    @Mapping(source = "empId", target = "emp")
    Personal toEntity(PersonalDTO personalDTO);

    default Personal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Personal personal = new Personal();
        personal.setId(id);
        return personal;
    }
}
