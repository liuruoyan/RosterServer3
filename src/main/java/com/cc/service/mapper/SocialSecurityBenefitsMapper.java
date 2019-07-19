package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.SocialSecurityBenefitsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SocialSecurityBenefits} and its DTO {@link SocialSecurityBenefitsDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnumPfTypeMapper.class, EnumPfStatusMapper.class, EnumPfPaySchemeMapper.class, EnumSsPaySchemeMapper.class, EnumSsStatusMapper.class, EnumEmpLaborTypeMapper.class, EnumEmpTaxerTypeMapper.class, EnumEmpTaxAreaMapper.class, EmployeeMapper.class})
public interface SocialSecurityBenefitsMapper extends EntityMapper<SocialSecurityBenefitsDTO, SocialSecurityBenefits> {

    @Mapping(source = "pfType.id", target = "pfTypeId")
    @Mapping(source = "pfStatus.id", target = "pfStatusId")
    @Mapping(source = "providentPayScheme.id", target = "providentPaySchemeId")
    @Mapping(source = "socialSecurityPayScheme.id", target = "socialSecurityPaySchemeId")
    @Mapping(source = "ssStatus.id", target = "ssStatusId")
    @Mapping(source = "laborType.id", target = "laborTypeId")
    @Mapping(source = "taxerType.id", target = "taxerTypeId")
    @Mapping(source = "taxArea.id", target = "taxAreaId")
    @Mapping(source = "emp.id", target = "empId")
    SocialSecurityBenefitsDTO toDto(SocialSecurityBenefits socialSecurityBenefits);

    @Mapping(source = "pfTypeId", target = "pfType")
    @Mapping(source = "pfStatusId", target = "pfStatus")
    @Mapping(source = "providentPaySchemeId", target = "providentPayScheme")
    @Mapping(source = "socialSecurityPaySchemeId", target = "socialSecurityPayScheme")
    @Mapping(source = "ssStatusId", target = "ssStatus")
    @Mapping(source = "laborTypeId", target = "laborType")
    @Mapping(source = "taxerTypeId", target = "taxerType")
    @Mapping(source = "taxAreaId", target = "taxArea")
    @Mapping(source = "empId", target = "emp")
    SocialSecurityBenefits toEntity(SocialSecurityBenefitsDTO socialSecurityBenefitsDTO);

    default SocialSecurityBenefits fromId(Long id) {
        if (id == null) {
            return null;
        }
        SocialSecurityBenefits socialSecurityBenefits = new SocialSecurityBenefits();
        socialSecurityBenefits.setId(id);
        return socialSecurityBenefits;
    }
}
