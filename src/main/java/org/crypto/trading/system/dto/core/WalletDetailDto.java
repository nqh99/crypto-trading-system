package org.crypto.trading.system.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public class WalletDetailDto {
  @EqualsAndHashCode.Include private UUID id;
  private CryptoDto crypto;
  private BigDecimal amount;
}
