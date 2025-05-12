package org.crypto.trading.system.dto.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TradeOrderDto {
  @NotEmpty @UUID private String userId;

  @NotEmpty @UUID private String cryptoId;

  @NotEmpty private String symbol;

  @NotEmpty private String orderType;

  @NotNull
  @Pattern(regexp = "^[BbSs]$", message = "must be either 'B' or 'S', case insensitive")
  private String bs;

  @PositiveOrZero private BigDecimal qty;

  @PositiveOrZero private BigDecimal filledQty;

  @PositiveOrZero private BigDecimal price;

  private String status;
}
