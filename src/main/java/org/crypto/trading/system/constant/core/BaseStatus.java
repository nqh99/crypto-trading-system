package org.crypto.trading.system.constant.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BaseStatus {
  NORMAL("N"),
  DELISTED("D");

  private final String status;
}
