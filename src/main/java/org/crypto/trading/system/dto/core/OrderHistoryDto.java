package org.crypto.trading.system.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderHistoryDto {
  @EqualsAndHashCode.Include private UUID id;
  private UserDto user;
  private CryptoDto crypto;
  private String orderType;
  private BigDecimal price;
  private BigDecimal qty;
  private BigDecimal filledQty;
  private String bs;
  private String status;
}
