package org.crypto.trading.system.controller;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.crypto.trading.system.dto.core.CryptoDto;
import org.crypto.trading.system.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("trade")
public class TradingController {

  private final TradingService tradingService;

  @Autowired
  public TradingController(TradingService tradingService) {
    this.tradingService = tradingService;
  }

  @GetMapping("aggregated-price")
  public ResponseEntity<List<CryptoDto>> retrieveLatestAggregatedPrice(
      @RequestParam @NotEmpty String symbol) {
    return ResponseEntity.ok(tradingService.retrieveLatestAggregatedPrice(symbol));
  }
}
