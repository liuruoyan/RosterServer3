package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumGenderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumGender} and its DTO {@link EnumGenderDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumGenderMapper extends EntityMapper<EnumGenderDTO, EnumGender> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumGenderDTO toDto(EnumGender enumGender);

    @Mapping(source = "parentId", target = "parent")
    EnumGender toEntity(EnumGenderDTO enumGenderDTO);

    default EnumGender fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumGender enumGender = new EnumGender();
        enumGender.setId(id);
        return enumGender;
    }
}
