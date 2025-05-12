package org.crypto.trading.system.constant.trade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {
  PENDING("P"),
  FULL_FILLED("FF"),
  PARTIAL_FILLED("PF"),
  CANCELED("C");

  private final String status;
}
