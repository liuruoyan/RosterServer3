package com.cc.service.mapper;

import com.cc.domain.*;
import com.cc.service.dto.PayCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PayCard} and its DTO {@link PayCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {EmployeeMapper.class})
public interface PayCardMapper extends EntityMapper<PayCardDTO, PayCard> {

    @Mapping(source = "emp.id", target = "empId")
    PayCardDTO toDto(PayCard payCard);

    @Mapping(source = "empId", target = "emp")
    PayCard toEntity(PayCardDTO payCardDTO);

    default PayCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        PayCard payCard = new PayCard();
        payCard.setId(id);
        return payCard;
    }
}
