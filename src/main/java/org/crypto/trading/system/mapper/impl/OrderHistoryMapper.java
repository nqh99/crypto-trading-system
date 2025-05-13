package org.crypto.trading.system.mapper.impl;

import org.crypto.trading.system.entity.Order;
import org.crypto.trading.system.entity.OrderHistory;
import org.crypto.trading.system.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderHistoryMapper extends IMapper<Order, OrderHistory> {
  OrderHistoryMapper INSTANCE = Mappers.getMapper(OrderHistoryMapper.class);
}
