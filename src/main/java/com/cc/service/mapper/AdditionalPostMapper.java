package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.AdditionalPostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdditionalPost} and its DTO {@link AdditionalPostDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface AdditionalPostMapper extends EntityMapper<AdditionalPostDTO, AdditionalPost> {

    @Mapping(source = "emp.id", target = "empId")
    AdditionalPostDTO toDto(AdditionalPost additionalPost);

    @Mapping(source = "empId", target = "emp")
    AdditionalPost toEntity(AdditionalPostDTO additionalPostDTO);

    default AdditionalPost fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdditionalPost additionalPost = new AdditionalPost();
        additionalPost.setId(id);
        return additionalPost;
    }
}
