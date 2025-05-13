package org.crypto.trading.system.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public class WalletDto {
  @EqualsAndHashCode.Include private UUID id;
  private String name;
  private BigDecimal cashBalance;
  private List<WalletDetailDto> walletDetailList;
  private String status;
}
