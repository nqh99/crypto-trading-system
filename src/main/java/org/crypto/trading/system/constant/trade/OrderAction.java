package org.crypto.trading.system.constant.trade;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderAction {
  BUY("B"),
  SELL("S");

  private final String action;

  public static OrderAction fromString(String action) {
    if (action == null || action.isEmpty()) {
      throw new IllegalArgumentException("Order action cannot be null or empty");
    }
    return Arrays.stream(OrderAction.values())
        .parallel()
        .filter(orderType -> orderType.getAction().equalsIgnoreCase(action))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("Unknown order action: " + action));
  }
}
