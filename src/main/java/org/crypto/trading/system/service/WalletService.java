package org.crypto.trading.system.service;

import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.crypto.trading.system.constant.core.BaseStatus;
import org.crypto.trading.system.dto.core.WalletDto;
import org.crypto.trading.system.entity.*;
import org.crypto.trading.system.exception.BusinessException;
import org.crypto.trading.system.mapper.impl.WalletMapper;
import org.crypto.trading.system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WalletService {

  private final UserRepository userRepository;
  private final WalletRepository walletRepository;

  @Autowired
  public WalletService(UserRepository userRepository, WalletRepository walletRepository) {
    this.userRepository = userRepository;
    this.walletRepository = walletRepository;
  }

  public List<WalletDto> retrieveWalletBalance(UUID userId) {
    User user =
        userRepository
            .findByIdAndStatus(userId, BaseStatus.NORMAL.getStatus())
            .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "User not found"));

    List<Wallet> wallet =
        walletRepository.findByUserIdAndStatus(user.getId(), BaseStatus.NORMAL.getStatus());

    if (wallet.isEmpty()) {
      throw new BusinessException(HttpStatus.NOT_FOUND, "User wallets not found");
    }

    return WalletMapper.INSTANCE.toDtoList(wallet);
  }
}
