package org.crypto.trading.system.mapper.impl;

import org.crypto.trading.system.dto.core.OrderHistoryDto;
import org.crypto.trading.system.entity.OrderHistory;
import org.crypto.trading.system.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {UserMapper.class})
public interface OrderHistoryDtoMapper extends IMapper<OrderHistory, OrderHistoryDto> {
  OrderHistoryDtoMapper INSTANCE = Mappers.getMapper(OrderHistoryDtoMapper.class);

  @Mapping(target = "user", source = "user", qualifiedByName = "UserMinimalDto")
  OrderHistoryDto toDto(OrderHistory orderHistory);
}
