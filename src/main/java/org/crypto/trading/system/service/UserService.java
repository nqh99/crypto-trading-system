package org.crypto.trading.system.service;

import java.util.List;
import java.util.UUID;
import org.crypto.trading.system.dto.core.OrderHistoryDto;
import org.crypto.trading.system.entity.*;
import org.crypto.trading.system.exception.BusinessException;
import org.crypto.trading.system.mapper.impl.OrderHistoryDtoMapper;
import org.crypto.trading.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final OrderHistoryRepository orderHistoryRepository;

  @Autowired
  public UserService(UserRepository userRepository, OrderHistoryRepository orderHistoryRepository) {
    this.userRepository = userRepository;
    this.orderHistoryRepository = orderHistoryRepository;
  }

  public List<OrderHistoryDto> retrieveTradeHistory(UUID userId) {
    User user =
        userRepository
            .findByIdAndStatus(userId, "N")
            .orElseThrow(
                () -> new BusinessException(HttpStatus.BAD_REQUEST, "User not found!", userId));

    List<OrderHistory> orderHistoryList = orderHistoryRepository.findAllByUserId(user.getId());

    return OrderHistoryDtoMapper.INSTANCE.toDtoList(orderHistoryList);
  }
}
