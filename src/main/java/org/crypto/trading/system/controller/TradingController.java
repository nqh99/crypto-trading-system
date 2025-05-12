package org.crypto.trading.system.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import org.crypto.trading.system.dto.core.CryptoDto;
import org.crypto.trading.system.dto.core.TradeOrderDto;
import org.crypto.trading.system.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping("new-trade")
  public ResponseEntity<TradeOrderDto> createNewTrade(
      @RequestBody @Valid TradeOrderDto tradeOrderDto) {
    return ResponseEntity.ok(tradingService.executeTrade(tradeOrderDto));
  }
}
