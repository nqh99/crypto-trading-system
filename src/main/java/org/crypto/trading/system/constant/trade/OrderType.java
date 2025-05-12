package org.crypto.trading.system.constant.trade;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderType {
  LIMIT("L"),
  MARKET("M");

  private final String type;

  public static OrderType fromString(String type) {
    if (type == null || type.isEmpty()) {
      throw new IllegalArgumentException("Order type cannot be null or empty");
    }
    return Arrays.stream(OrderType.values())
        .parallel()
        .filter(orderType -> orderType.getType().equalsIgnoreCase(type))
        .findAny()
        .orElseThrow(() -> new IllegalArgumentException("Unknown order type: " + type));
  }
}
