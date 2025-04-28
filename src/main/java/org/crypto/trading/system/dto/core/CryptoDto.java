package org.crypto.trading.system.dto.core;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public class CryptoDto {

  @UUID @EqualsAndHashCode.Include private String id;

  private String symbol;

  private BigDecimal open;

  private BigDecimal high;

  private BigDecimal low;

  private BigDecimal close;

  @JsonAlias({"bid", "bidPrice"})
  private BigDecimal bid;

  @JsonAlias({"bidSize", "bidQty"})
  private BigDecimal bidQty;

  @JsonAlias({"ask", "askPrice"})
  private BigDecimal ask;

  @JsonAlias({"askSize", "askQty"})
  private BigDecimal askQty;

  @EqualsAndHashCode.Include private String status;
}
