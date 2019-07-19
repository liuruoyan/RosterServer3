package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Employee} and its DTO {@link EmployeeDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnumEmpStatusMapper.class, EnumIdTypeMapper.class, EnumContractTypeMapper.class, EnumEmpTypeMapper.class, EnumGenderMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "idType.id", target = "idTypeId")
    @Mapping(source = "contractType.id", target = "contractTypeId")
    @Mapping(source = "empType.id", target = "empTypeId")
    @Mapping(source = "gender.id", target = "genderId")
    EmployeeDTO toDto(Employee employee);

    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "removeContracts", ignore = true)
    @Mapping(target = "personals", ignore = true)
    @Mapping(target = "removePersonals", ignore = true)
    @Mapping(target = "socialSecurityBenefits", ignore = true)
    @Mapping(target = "removeSocialSecurityBenefits", ignore = true)
    @Mapping(target = "payCards", ignore = true)
    @Mapping(target = "removePayCards", ignore = true)
    @Mapping(target = "dimissions", ignore = true)
    @Mapping(target = "removeDimissions", ignore = true)
    @Mapping(target = "workExperiences", ignore = true)
    @Mapping(target = "removeWorkExperiences", ignore = true)
    @Mapping(target = "educationExperiences", ignore = true)
    @Mapping(target = "removeEducationExperiences", ignore = true)
    @Mapping(target = "directSupervisors", ignore = true)
    @Mapping(target = "removeDirectSupervisors", ignore = true)
    @Mapping(target = "additionalPosts", ignore = true)
    @Mapping(target = "removeAdditionalPosts", ignore = true)
    @Mapping(source = "statusId", target = "status")
    @Mapping(source = "idTypeId", target = "idType")
    @Mapping(source = "contractTypeId", target = "contractType")
    @Mapping(source = "empTypeId", target = "empType")
    @Mapping(source = "genderId", target = "gender")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
