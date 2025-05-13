package org.crypto.trading.system.controller;

import java.util.List;
import java.util.UUID;
import org.crypto.trading.system.dto.core.OrderHistoryDto;
import org.crypto.trading.system.dto.core.WalletDto;
import org.crypto.trading.system.service.UserService;
import org.crypto.trading.system.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;
  private final WalletService walletService;

  @Autowired
  public UserController(UserService userService, WalletService walletService) {
    this.userService = userService;
    this.walletService = walletService;
  }

  @GetMapping("/{userId}/trade-history")
  public ResponseEntity<List<OrderHistoryDto>> retrieveTradeHistory(
      @PathVariable("userId") UUID userId) {
    return ResponseEntity.ok(userService.retrieveTradeHistory(userId));
  }

  @GetMapping("/{userId}/balance")
  public ResponseEntity<List<WalletDto>> getWalletBalance(@PathVariable("userId") UUID userId) {
    return ResponseEntity.ok(walletService.retrieveWalletBalance(userId));
  }
}
