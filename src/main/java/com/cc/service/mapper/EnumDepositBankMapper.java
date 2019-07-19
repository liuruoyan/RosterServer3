package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.EnumDepositBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EnumDepositBank} and its DTO {@link EnumDepositBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnumDepositBankMapper extends EntityMapper<EnumDepositBankDTO, EnumDepositBank> {

    @Mapping(source = "parent.id", target = "parentId")
    EnumDepositBankDTO toDto(EnumDepositBank enumDepositBank);

    @Mapping(source = "parentId", target = "parent")
    EnumDepositBank toEntity(EnumDepositBankDTO enumDepositBankDTO);

    default EnumDepositBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        EnumDepositBank enumDepositBank = new EnumDepositBank();
        enumDepositBank.setId(id);
        return enumDepositBank;
    }
}
