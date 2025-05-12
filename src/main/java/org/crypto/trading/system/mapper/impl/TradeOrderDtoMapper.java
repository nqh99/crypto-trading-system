package org.crypto.trading.system.mapper.impl;

import org.crypto.trading.system.dto.core.TradeOrderDto;
import org.crypto.trading.system.entity.Order;
import org.crypto.trading.system.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TradeOrderDtoMapper extends IMapper<Order, TradeOrderDto> {
  TradeOrderDtoMapper INSTANCE = Mappers.getMapper(TradeOrderDtoMapper.class);

  @Mapping(target = "userId", source = "order.user.id")
  @Mapping(target = "symbol", source = "order.crypto.symbol")
  @Mapping(target = "cryptoId", source = "order.crypto.id")
  TradeOrderDto toDto(Order order);
}
