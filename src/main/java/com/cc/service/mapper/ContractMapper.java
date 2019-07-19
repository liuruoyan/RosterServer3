package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.ContractDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Contract} and its DTO {@link ContractDTO}.
 */
@Mapper(componentModel = "spring", uses = {EnumContractTypeMapper.class, EmployeeMapper.class})
public interface ContractMapper extends EntityMapper<ContractDTO, Contract> {

    @Mapping(source = "contractType.id", target = "contractTypeId")
    @Mapping(source = "emp.id", target = "empId")
    ContractDTO toDto(Contract contract);

    @Mapping(source = "contractTypeId", target = "contractType")
    @Mapping(source = "empId", target = "emp")
    Contract toEntity(ContractDTO contractDTO);

    default Contract fromId(Long id) {
        if (id == null) {
            return null;
        }
        Contract contract = new Contract();
        contract.setId(id);
        return contract;
    }
}
